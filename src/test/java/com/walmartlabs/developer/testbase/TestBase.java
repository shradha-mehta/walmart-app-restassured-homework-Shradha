package com.walmartlabs.developer.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;


public class TestBase {
public static final String API_KEY = "75e3u4sgb2khg673cbv2gjup";
    @BeforeClass
    public static void inIT() {

        RestAssured.baseURI = "http://api.walmartlabs.com";
        RestAssured.basePath = "/v1";
    }
    }
