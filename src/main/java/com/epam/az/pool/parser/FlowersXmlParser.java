package com.epam.az.pool.parser;

import java.lang.reflect.*;


public abstract class FlowersXmlParser implements XmlParser {
    protected StringBuilder stringBuilder = new StringBuilder();

    protected Class elementClazz;
    protected Method elementMethod;

    private String rootItemName;
    private String rootName;
    private FlowerStack flowerStack;
    private Class rootItemClass;
    private Object rootInstance;
    private Method addObjectInList;

    public void configureParser(Class rootClass, String rootItemListName) {
        try {
            Field field = rootClass.getDeclaredField(rootItemListName);
            ParameterizedType type = (ParameterizedType) field.getGenericType();
            Type[] typeArguments = type.getActualTypeArguments();
            rootItemClass = (Class) typeArguments[0];

            rootItemName = rootItemClass.getSimpleName();
            rootName = rootClass.getSimpleName();
            rootInstance = rootClass.newInstance();

            Class returnType = rootClass.getMethod("get", int.class).getReturnType();
            addObjectInList = rootClass.getDeclaredMethod("add", returnType);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    protected Object getResult() {
        return rootInstance;
    }

    protected void elementStart(String qName) {
        try {
            if (qName.equalsIgnoreCase(rootItemName)) {
                flowerStack = new FlowerStack();
                Object item = rootItemClass.newInstance();
                flowerStack.push(item);
            } else if (qName.equalsIgnoreCase(rootName)) {

            } else {
                Method getMethod = flowerStack.getLast().getClass().getMethod("get" + upFirstChar(qName), null);
                elementMethod = flowerStack.getLast().getClass().getMethod("set" + upFirstChar(qName), getMethod.getReturnType());
                elementClazz = getMethod.getReturnType();
                if (!elementClazz.isPrimitive() && elementClazz != String.class) {
                    pushObjectInStack(elementClazz.getDeclaredMethods(), elementClazz.newInstance(), flowerStack);
                }
            }

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected void chars(String string) {
        stringBuilder.append(string);
    }

    protected void elementEnd(String qName) {
        if (qName.equalsIgnoreCase(rootItemName)) {
            try {
                addObjectInList.invoke(rootInstance, flowerStack.pop());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (qName.equalsIgnoreCase(rootName)) {

        } else if (qName.equalsIgnoreCase(flowerStack.getLast().getClass().getSimpleName())) {
            Object value = flowerStack.pop();
            invokeSetterForObject(flowerStack.pop(), value);
        } else {
            String characterValue = stringBuilder.toString().replaceAll("\\s", "");
            Object value = stringToObjectType(characterValue, elementClazz);
            invokeSetterForPrimitive(flowerStack.pop(), value);
        }
        stringBuilder = new StringBuilder();
    }

    public Object stringToObjectType(String str, Class aclass) {
        if (aclass.getSimpleName() == "int") {
            try {
                int result = Integer.parseInt(str);
                return result;
            } catch (NumberFormatException e) {
            }
        }
        if (aclass.getSimpleName().equalsIgnoreCase("boolean")) {
            if (str.equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }
        }
        return str;
    }

    protected void pushObjectInStack(Method[] methods, Object object, FlowerStack flowerStack) {
        for (Method method1 : methods) {
            if (method1.getName().startsWith("get")) {
                flowerStack.push(object);
            }
        }
        flowerStack.push(object);
    }

    protected void invokeSetterForPrimitive(Object inputClass, Object value) {
        try {
            elementMethod.invoke(inputClass, value);
        } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    protected void invokeSetterForObject(Object inputClass, Object value) {
        try {
            getSetterMethodForValue(inputClass.getClass(), value).invoke(inputClass, value);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected String upFirstChar(String string) {
        char[] charArray = string.toCharArray();
        charArray[0] = Character.toUpperCase(charArray[0]);
        String result = new String(charArray);
        return result;
    }

    private Method getSetterMethodForValue(Class aclass, Object value) {
        Method setMethod = null;
        try {
            Method getMethod = aclass.getMethod("get" + value.getClass().getSimpleName(), null);
            setMethod = aclass.getMethod("set" + value.getClass().getSimpleName(), getMethod.getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return setMethod;
    }
}

