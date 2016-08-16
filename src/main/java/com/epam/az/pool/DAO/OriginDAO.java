package com.epam.az.pool.DAO;

import com.epam.az.pool.entity.Origin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OriginDAO extends AbstractDAO<Origin> {
   public OriginDAO(){
       setMainClas(Origin.class);

   }


}
