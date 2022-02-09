package com.example.BikeRentalSystem.RestEndpoints;

public class UserEndpoints extends ConstantRestEndpoints {

    public static final String GET_ALL=API+"users";
    public static final String POST=API+"users";
    public static final String GET_BY_STATIONID=API+"users/{id}";
    public static final String UPDATE_BY_INVENTORYID=API+"users/{id}";
    public static final String GET_BY_EMAIL=API+"users/get-by-email/{email}";
    public static final String PUT_ENDPOINT=API+"/users/{id}";

}
