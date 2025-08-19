package com.pms.MediCore.dto.request;

public class NameSearchRequest {


    private String schema;
    private String tableName;
    private String search;

    public NameSearchRequest(){

    }
    public NameSearchRequest(String schema, String tableName, String search) {
        this.schema = schema;
        this.tableName = tableName;
        this.search = search;
    }

    public String getSchema() {
        return schema;
    }
    public void setSchema(String schema) {
        this.schema = schema;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
}
