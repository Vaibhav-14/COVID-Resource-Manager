package com.mycompany.entity;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"center_id",
"name",
"address",
"state_name",
"district_name",
"block_name",
"pincode",
"from",
"to",
"lat",
"long",
"fee_type",
"session_id",
"date",
"available_capacity",
"available_capacity_dose1",
"available_capacity_dose2",
"fee",
"min_age_limit",
"allow_all_age",
"vaccine",
"slots"
})
@Generated("jsonschema2pojo")
public class Session{

@JsonProperty("center_id")
private int centerId;
@JsonProperty("name")
private String name;
@JsonProperty("address")
private String address;
@JsonProperty("state_name")
private String stateName;
@JsonProperty("district_name")
private String districtName;
@JsonProperty("block_name")
private String blockName;
@JsonProperty("pincode")
private int pincode;
@JsonProperty("from")
private String from;
@JsonProperty("to")
private String to;
@JsonProperty("lat")
private int lat;
@JsonProperty("long")
private int _long;
@JsonProperty("fee_type")
private String feeType;
@JsonProperty("session_id")
private String sessionId;
@JsonProperty("date")
private String date;
@JsonProperty("available_capacity")
private int availableCapacity;
@JsonProperty("available_capacity_dose1")
private int availableCapacityDose1;
@JsonProperty("available_capacity_dose2")
private int availableCapacityDose2;
@JsonProperty("fee")
private String fee;
@JsonProperty("min_age_limit")
private int minAgeLimit;
@JsonProperty("allow_all_age")
private boolean allowAllAge;
@JsonProperty("vaccine")
private String vaccine;
@JsonProperty("slots")
private List<String> slots = null;


public Session() {
}

/**
*
* @param date
* @param centerId
* @param pincode
* @param availableCapacity
* @param minAgeLimit
* @param address
* @param districtName
* @param blockName
* @param fee
* @param sessionId
* @param feeType
* @param availableCapacityDose1
* @param availableCapacityDose2
* @param vaccine
* @param slots
* @param stateName
* @param _long
* @param name
* @param from
* @param to
* @param lat
* @param allowAllAge
*/
public Session(int centerId, String name, String address, String stateName, String districtName, String blockName, int pincode, String from, String to, int lat, int _long, String feeType, String sessionId, String date, int availableCapacity, int availableCapacityDose1, int availableCapacityDose2, String fee, int minAgeLimit, boolean allowAllAge, String vaccine, List<String> slots) {
this.centerId = centerId;
this.name = name;
this.address = address;
this.stateName = stateName;
this.districtName = districtName;
this.blockName = blockName;
this.pincode = pincode;
this.from = from;
this.to = to;
this.lat = lat;
this._long = _long;
this.feeType = feeType;
this.sessionId = sessionId;
this.date = date;
this.availableCapacity = availableCapacity;
this.availableCapacityDose1 = availableCapacityDose1;
this.availableCapacityDose2 = availableCapacityDose2;
this.fee = fee;
this.minAgeLimit = minAgeLimit;
this.allowAllAge = allowAllAge;
this.vaccine = vaccine;
this.slots = slots;
}

@JsonProperty("center_id")
public int getCenterId() {
return centerId;
}

@JsonProperty("center_id")
public void setCenterId(int centerId) {
this.centerId = centerId;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("address")
public String getAddress() {
return address;
}

@JsonProperty("address")
public void setAddress(String address) {
this.address = address;
}

@JsonProperty("state_name")
public String getStateName() {
return stateName;
}

@JsonProperty("state_name")
public void setStateName(String stateName) {
this.stateName = stateName;
}

@JsonProperty("district_name")
public String getDistrictName() {
return districtName;
}

@JsonProperty("district_name")
public void setDistrictName(String districtName) {
this.districtName = districtName;
}

@JsonProperty("block_name")
public String getBlockName() {
return blockName;
}

@JsonProperty("block_name")
public void setBlockName(String blockName) {
this.blockName = blockName;
}

@JsonProperty("pincode")
public int getPincode() {
return pincode;
}

@JsonProperty("pincode")
public void setPincode(int pincode) {
this.pincode = pincode;
}

@JsonProperty("from")
public String getFrom() {
return from;
}

@JsonProperty("from")
public void setFrom(String from) {
this.from = from;
}

@JsonProperty("to")
public String getTo() {
return to;
}

@JsonProperty("to")
public void setTo(String to) {
this.to = to;
}

@JsonProperty("lat")
public int getLat() {
return lat;
}

@JsonProperty("lat")
public void setLat(int lat) {
this.lat = lat;
}

@JsonProperty("long")
public int getLong() {
return _long;
}

@JsonProperty("long")
public void setLong(int _long) {
this._long = _long;
}

@JsonProperty("fee_type")
public String getFeeType() {
return feeType;
}

@JsonProperty("fee_type")
public void setFeeType(String feeType) {
this.feeType = feeType;
}

@JsonProperty("session_id")
public String getSessionId() {
return sessionId;
}

@JsonProperty("session_id")
public void setSessionId(String sessionId) {
this.sessionId = sessionId;
}

@JsonProperty("date")
public String getDate() {
return date;
}

@JsonProperty("date")
public void setDate(String date) {
this.date = date;
}

@JsonProperty("available_capacity")
public int getAvailableCapacity() {
return availableCapacity;
}

@JsonProperty("available_capacity")
public void setAvailableCapacity(int availableCapacity) {
this.availableCapacity = availableCapacity;
}

@JsonProperty("available_capacity_dose1")
public int getAvailableCapacityDose1() {
return availableCapacityDose1;
}

@JsonProperty("available_capacity_dose1")
public void setAvailableCapacityDose1(int availableCapacityDose1) {
this.availableCapacityDose1 = availableCapacityDose1;
}

@JsonProperty("available_capacity_dose2")
public int getAvailableCapacityDose2() {
return availableCapacityDose2;
}

@JsonProperty("available_capacity_dose2")
public void setAvailableCapacityDose2(int availableCapacityDose2) {
this.availableCapacityDose2 = availableCapacityDose2;
}

@JsonProperty("fee")
public String getFee() {
return fee;
}

@JsonProperty("fee")
public void setFee(String fee) {
this.fee = fee;
}

@JsonProperty("min_age_limit")
public int getMinAgeLimit() {
return minAgeLimit;
}

@JsonProperty("min_age_limit")
public void setMinAgeLimit(int minAgeLimit) {
this.minAgeLimit = minAgeLimit;
}

@JsonProperty("allow_all_age")
public boolean isAllowAllAge() {
return allowAllAge;
}

@JsonProperty("allow_all_age")
public void setAllowAllAge(boolean allowAllAge) {
this.allowAllAge = allowAllAge;
}

@JsonProperty("vaccine")
public String getVaccine() {
return vaccine;
}

@JsonProperty("vaccine")
public void setVaccine(String vaccine) {
this.vaccine = vaccine;
}

@JsonProperty("slots")
public List<String> getSlots() {
return slots;
}

@JsonProperty("slots")
public void setSlots(List<String> slots) {
this.slots = slots;
}

//@Override
//public String toString() {
//StringBuilder sb = new StringBuilder();
//sb.append('[');
//sb.append("centerId");
//sb.append('=');
//sb.append(this.centerId);
//sb.append(',');
//sb.append("name");
//sb.append('=');
//sb.append(((this.name == null)?"<null>":this.name));
//sb.append(',');
//sb.append("address");
//sb.append('=');
//sb.append(((this.address == null)?"<null>":this.address));
//sb.append(',');
//sb.append("stateName");
//sb.append('=');
//sb.append(((this.stateName == null)?"<null>":this.stateName));
//sb.append(',');
//sb.append("districtName");
//sb.append('=');
//sb.append(((this.districtName == null)?"<null>":this.districtName));
//sb.append(',');
//sb.append("blockName");
//sb.append('=');
//sb.append(((this.blockName == null)?"<null>":this.blockName));
//sb.append(',');
//sb.append("pincode");
//sb.append('=');
//sb.append(this.pincode);
//sb.append(',');
//sb.append("from");
//sb.append('=');
//sb.append(((this.from == null)?"<null>":this.from));
//sb.append(',');
//sb.append("to");
//sb.append('=');
//sb.append(((this.to == null)?"<null>":this.to));
//sb.append(',');
//sb.append("lat");
//sb.append('=');
//sb.append(this.lat);
//sb.append(',');
//sb.append("_long");
//sb.append('=');
//sb.append(this._long);
//sb.append(',');
//sb.append("feeType");
//sb.append('=');
//sb.append(((this.feeType == null)?"<null>":this.feeType));
//sb.append(',');
//sb.append("sessionId");
//sb.append('=');
//sb.append(((this.sessionId == null)?"<null>":this.sessionId));
//sb.append(',');
//sb.append("date");
//sb.append('=');
//sb.append(((this.date == null)?"<null>":this.date));
//sb.append(',');
//sb.append("availableCapacity");
//sb.append('=');
//sb.append(this.availableCapacity);
//sb.append(',');
//sb.append("availableCapacityDose1");
//sb.append('=');
//sb.append(this.availableCapacityDose1);
//sb.append(',');
//sb.append("availableCapacityDose2");
//sb.append('=');
//sb.append(this.availableCapacityDose2);
//sb.append(',');
//sb.append("fee");
//sb.append('=');
//sb.append(((this.fee == null)?"<null>":this.fee));
//sb.append(',');
//sb.append("minAgeLimit");
//sb.append('=');
//sb.append(this.minAgeLimit);
//sb.append(',');
//sb.append("allowAllAge");
//sb.append('=');
//sb.append(this.allowAllAge);
//sb.append(',');
//sb.append("vaccine");
//sb.append('=');
//sb.append(((this.vaccine == null)?"<null>":this.vaccine));
//sb.append(',');
//sb.append("slots");
//sb.append('=');
//sb.append(((this.slots == null)?"<null>":this.slots));
//return sb.toString();
//}

}