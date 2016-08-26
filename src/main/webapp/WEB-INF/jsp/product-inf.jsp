<%--@elvariable id="product" type="com.epam.az.flower.shop.entity.Product"--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${product.flower.name}</title>
</head>
<body>

Product type : ${product.flower.name}<br>
<br>
Description : ${product.description}
Made place : ${product.origin.country} - ${product.origin.province}
Price : ${product.price}
<br>
VisualParameters: ${product.flower.visualParameters}<br>
Leaves color : ${product.flower.visualParameters.colorLeaves}<br>
Steam color : ${product.flower.visualParameters.colorSteam}<br>
Average height : ${product.flower.visualParameters.averageHeight}<br>
<br>
Growing condition: ${product.flower.growingCondition.name}<br>
Love light: ${product.flower.growingCondition.lovelight}<br>
temperature: ${product.flower.growingCondition.temperature.min} - ${product.flower.growingCondition.temperature.max}<br>
Water in week: ${product.flower.growingCondition.waterInWeek.min} - ${product.flower.growingCondition.waterInWeek.max}<br>


</body>
</html>
