package com.groot.userservice;


import com.groot.camel.common.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = Constants.US_NAMESPACE, name = "GetUserByIdRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class GetUserByIdRequest {

    @XmlElement(namespace = Constants.US_NAMESPACE)
    private String userId;
}
