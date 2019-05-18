package com.groot.userservice;


import com.groot.camel.common.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(namespace = Constants.US_NAMESPACE, name = "GetUserByIdResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class GetUserByIdResponse {

    @XmlElement(namespace = Constants.US_NAMESPACE)
    private String username;

    @XmlElement(namespace = Constants.US_NAMESPACE)
    private String gender;

    @XmlElement(namespace = Constants.US_NAMESPACE)
    private Date birthday;

    @XmlElement(namespace = Constants.US_NAMESPACE)
    private String location;
}
