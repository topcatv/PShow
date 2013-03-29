/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pshow.repo.dao.model;

import java.io.Serializable;

/**
 * @author roy
 * 
 */
public class PropertyModel {

    private long         contentId;
    private int          actualType;
    private long         propertyQName;
    private String       stringValue;
    private Long         longValue;
    private Float        floatValue;
    private Double       doubleValue;
    private Boolean      booleanValue;
    private Serializable serializableValue;

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public int getActualType() {
        return actualType;
    }

    public void setActualType(int actualType) {
        this.actualType = actualType;
    }

    public long getPropertyQName() {
        return propertyQName;
    }

    public void setPropertyQName(long propertyQName) {
        this.propertyQName = propertyQName;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Serializable getSerializableValue() {
        return serializableValue;
    }

    public void setSerializableValue(Serializable serializableValue) {
        this.serializableValue = serializableValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

}
