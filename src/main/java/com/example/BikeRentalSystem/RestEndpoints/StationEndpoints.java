package com.example.BikeRentalSystem.RestEndpoints;

public class StationEndpoints extends ConstantRestEndpoints{

    public static final String GET_ALL=API+"stations";
    public static final String POST=API+"stations";
    public static final String GET_BY_CITY=API+"stations/{city}";
    public static final String GET_BY_ID=API+"station/{id}";
    public static final String DELETE_BY_ID=API+"stations/{id}";
    public static final String UPDATE_BY_ID=API+"stations/{id}";

}
