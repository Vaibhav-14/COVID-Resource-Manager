package com.mycompany.entity;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"sessions"
})
@Generated("jsonschema2pojo")
public class CenterDetails {

@JsonProperty("sessions")
private List<Session> sessions = null;

public CenterDetails() {
}


/**
*
* @param sessions
*/

public CenterDetails(List<Session> sessions) {
super();
this.sessions = sessions;
}

@JsonProperty("sessions")
public List<Session> getSessions() {
return sessions;
}

@JsonProperty("sessions")
public void setSessions(List<Session> sessions) {
this.sessions = sessions;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(((this.sessions == null)?"<null>":this.sessions));
return sb.toString();
}

}