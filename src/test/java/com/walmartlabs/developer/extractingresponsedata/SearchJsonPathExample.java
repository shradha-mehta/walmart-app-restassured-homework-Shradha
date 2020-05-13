package com.walmartlabs.developer.extractingresponsedata;

import com.walmartlabs.developer.testbase.TestBase;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;


public class SearchJsonPathExample  extends TestBase {
    // 1) Extract numItems
    @Test
    public void test001() {
        int numItem = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("numItems");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of items are: " + numItem);
        System.out.println("------------------End of Test---------------------------");

    }

    // 2) Extract query
    @Test
    public void test002() {
        // homework
        String query1 = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("query");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The search query is: "+ query1);
        System.out.println("------------------End of Test---------------------------");

    }

    // 3) Extract first productName by providing list index value
    @Test
    public void test003() {
        String itemName = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items[0].name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product name is: " + itemName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 4) Get the first list from imageEntities for the first product
    @Test
    public void test004() {

       HashMap<Object,String> listImage = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items[0].imageEntities[0]");



        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first list from imageEntities of the first product are: " + listImage);
        System.out.println("------------------End of Test---------------------------");

    }

    // 5)Print the size of items
    @Test
    public void test005() {
       Integer size = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.size");




        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the items is: " +size);
        System.out.println("------------------End of Test---------------------------");
    }

    // 6) Get All the Names from items
    @Test
    public void test006() {

        List<String> allItemsName = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the products are:: " + allItemsName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 7) Get the all the values for Name == Apple iPod touch 32GB  (Assorted Colors)
    @Test
    public void test007() {

        List<HashMap<String,Object>> values = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.findAll{it.name=='Apple iPod touch 32GB  (Assorted Colors)'}"); // groovy language

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values for item name Apple iPod touch 32GB  (Assorted Colors) are: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    // 8) Get the sale price for Name == Apple iPod touch 7th Generation 32GB - Space Gray (New Model)
    @Test
    public void test008() {
        List<HashMap<String, Object>> salePrice = given()

                .queryParam("query", "ipod")
                .queryParam("format", "json")
                .queryParam("apiKey", API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.findAll{it.name=='Apple iPod touch 7th Generation 32GB - Space Gray (New Model)'}.salePrice");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The sale price is " + salePrice);
        System.out.println("------------------End of Test---------------------------");


    }

    // 9) Get the Names which have salePrice less than 200
    @Test
    public void test009() {

        List<String> namesOfIpod = given() // String is key  and object is value (String , int , float , double)
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.findAll{it.salePrice<200}.name"); // groovy language




        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of items that price is less than 200 are: " + namesOfIpod);
        System.out.println("------------------End of Test---------------------------");
    }

    // 10) Get the msrp of items that Start with name = Ref
    @Test
    public void test010() {
        List<String> msrp = given() // String is key  and object is value (String , int , float , double)
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.findAll{it.name==~/Ref.*/}.msrp"); // groovy language for start with




        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The msrp of items that start with Ref are: " + msrp);
        System.out.println("------------------End of Test---------------------------");
    }

    // 11) Get the sale price of items that End with name =ed
    @Test
    public void test011() {

        List<String> salePrice = given()
                .queryParam("query", "ipod")
                .queryParam("format","json")
                .queryParam("apiKey",API_KEY)
                .when()
                .get("/search")
                .then()
                .extract().path("items.findAll{it.name==~/.*ing/}.salePrice"); // groovy language for end with




        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The salePrice of items that end with ed are: " + salePrice);
        System.out.println("------------------End of Test---------------------------");
    }



}
