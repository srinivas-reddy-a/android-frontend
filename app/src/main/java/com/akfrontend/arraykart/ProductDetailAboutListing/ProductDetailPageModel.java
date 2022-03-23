package com.akfrontend.arraykart.ProductDetailAboutListing;

import com.google.gson.annotations.SerializedName;

public class ProductDetailPageModel {
    private String id;
    private String name;
    private String technical_name;
    private String price;
    private String description;
    private String target_disease;
    private String target_field_crops;
    private String target_vegetable_crops;
    private String target_fruit_crops;
    private String target_plantation_crops;
    private String mode_of_action;
    private String duration_of_effect;
    private String compatability_with_other_chemicals;
    private String frequency_of_application;
    private String dosage;
    @SerializedName("water_requirement (ltr)")
    private String water_requirement;
    private String time_of_application;
    private String method_of_application;
    private String waiting_period;
    private String phytotoxicity;
    private String storage;
    private String country_of_origin;
    private String volume;
    private String dimensions;
    @SerializedName("state(solid/liquid)")
    private String state;
    private String category;
    private String inventory_id;
    private String brand;
    private String image;
    private String sowing_time;
    @SerializedName("seed_Rate(Kg/acre)")
    private String seed_Rate;
    private String maturity_duration;
    private String colour;
    private String usp;
    @SerializedName("Number of Seeds/Packet")
    private String NumberOfSeeds;

    public ProductDetailPageModel(String id, String name, String technical_name, String price, String description, String target_disease, String target_field_crops, String target_vegetable_crops, String target_fruit_crops, String target_plantation_crops, String mode_of_action, String duration_of_effect, String compatability_with_other_chemicals, String frequency_of_application, String dosage, String water_requirement, String time_of_application, String method_of_application, String waiting_period, String phytotoxicity, String storage, String country_of_origin, String volume, String dimensions, String state, String category, String inventory_id, String brand, String image, String sowing_time, String seed_Rate, String maturity_duration, String colour, String usp, String numberOfSeeds) {
        this.id = id;
        this.name = name;
        this.technical_name = technical_name;
        this.price = price;
        this.description = description;
        this.target_disease = target_disease;
        this.target_field_crops = target_field_crops;
        this.target_vegetable_crops = target_vegetable_crops;
        this.target_fruit_crops = target_fruit_crops;
        this.target_plantation_crops = target_plantation_crops;
        this.mode_of_action = mode_of_action;
        this.duration_of_effect = duration_of_effect;
        this.compatability_with_other_chemicals = compatability_with_other_chemicals;
        this.frequency_of_application = frequency_of_application;
        this.dosage = dosage;
        this.water_requirement = water_requirement;
        this.time_of_application = time_of_application;
        this.method_of_application = method_of_application;
        this.waiting_period = waiting_period;
        this.phytotoxicity = phytotoxicity;
        this.storage = storage;
        this.country_of_origin = country_of_origin;
        this.volume = volume;
        this.dimensions = dimensions;
        this.state = state;
        this.category = category;
        this.inventory_id = inventory_id;
        this.brand = brand;
        this.image = image;
        this.sowing_time = sowing_time;
        this.seed_Rate = seed_Rate;
        this.maturity_duration = maturity_duration;
        this.colour = colour;
        this.usp = usp;
        NumberOfSeeds = numberOfSeeds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnical_name() {
        return technical_name;
    }

    public void setTechnical_name(String technical_name) {
        this.technical_name = technical_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTarget_disease() {
        return target_disease;
    }

    public void setTarget_disease(String target_disease) {
        this.target_disease = target_disease;
    }

    public String getTarget_field_crops() {
        return target_field_crops;
    }

    public void setTarget_field_crops(String target_field_crops) {
        this.target_field_crops = target_field_crops;
    }

    public String getTarget_vegetable_crops() {
        return target_vegetable_crops;
    }

    public void setTarget_vegetable_crops(String target_vegetable_crops) {
        this.target_vegetable_crops = target_vegetable_crops;
    }

    public String getTarget_fruit_crops() {
        return target_fruit_crops;
    }

    public void setTarget_fruit_crops(String target_fruit_crops) {
        this.target_fruit_crops = target_fruit_crops;
    }

    public String getTarget_plantation_crops() {
        return target_plantation_crops;
    }

    public void setTarget_plantation_crops(String target_plantation_crops) {
        this.target_plantation_crops = target_plantation_crops;
    }

    public String getMode_of_action() {
        return mode_of_action;
    }

    public void setMode_of_action(String mode_of_action) {
        this.mode_of_action = mode_of_action;
    }

    public String getDuration_of_effect() {
        return duration_of_effect;
    }

    public void setDuration_of_effect(String duration_of_effect) {
        this.duration_of_effect = duration_of_effect;
    }

    public String getCompatability_with_other_chemicals() {
        return compatability_with_other_chemicals;
    }

    public void setCompatability_with_other_chemicals(String compatability_with_other_chemicals) {
        this.compatability_with_other_chemicals = compatability_with_other_chemicals;
    }

    public String getFrequency_of_application() {
        return frequency_of_application;
    }

    public void setFrequency_of_application(String frequency_of_application) {
        this.frequency_of_application = frequency_of_application;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getWater_requirement() {
        return water_requirement;
    }

    public void setWater_requirement(String water_requirement) {
        this.water_requirement = water_requirement;
    }

    public String getTime_of_application() {
        return time_of_application;
    }

    public void setTime_of_application(String time_of_application) {
        this.time_of_application = time_of_application;
    }

    public String getMethod_of_application() {
        return method_of_application;
    }

    public void setMethod_of_application(String method_of_application) {
        this.method_of_application = method_of_application;
    }

    public String getWaiting_period() {
        return waiting_period;
    }

    public void setWaiting_period(String waiting_period) {
        this.waiting_period = waiting_period;
    }

    public String getPhytotoxicity() {
        return phytotoxicity;
    }

    public void setPhytotoxicity(String phytotoxicity) {
        this.phytotoxicity = phytotoxicity;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(String inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSowing_time() {
        return sowing_time;
    }

    public void setSowing_time(String sowing_time) {
        this.sowing_time = sowing_time;
    }

    public String getSeed_Rate() {
        return seed_Rate;
    }

    public void setSeed_Rate(String seed_Rate) {
        this.seed_Rate = seed_Rate;
    }

    public String getMaturity_duration() {
        return maturity_duration;
    }

    public void setMaturity_duration(String maturity_duration) {
        this.maturity_duration = maturity_duration;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getUsp() {
        return usp;
    }

    public void setUsp(String usp) {
        this.usp = usp;
    }

    public String getNumberOfSeeds() {
        return NumberOfSeeds;
    }

    public void setNumberOfSeeds(String numberOfSeeds) {
        NumberOfSeeds = numberOfSeeds;
    }
}
