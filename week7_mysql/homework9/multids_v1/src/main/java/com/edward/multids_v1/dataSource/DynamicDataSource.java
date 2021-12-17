package com.edward.multids_v1.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public DynamicDataSource(DataSource masterDS, DataSource slave1Ds, DataSource slave2Ds){
        super.setDefaultTargetDataSource(masterDS);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("master", masterDS);
        map.put("slave1", slave1Ds);
        map.put("slave2", slave2Ds);
        super.setTargetDataSources(map);
        super.afterPropertiesSet();
    }


    @Override
    protected Object determineCurrentLookupKey() {
        return HOLDER.get();
    }

    public static void setDataSource(String dataSource){
        HOLDER.set(dataSource);
    }

    public static void clearDataSource(){
        HOLDER.remove();
    }
}
