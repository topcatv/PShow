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
package org.pshow.repo.datamodel.content;

import java.io.Serializable;
import java.util.Date;

import org.pshow.repo.dao.model.PropertyModel;
import org.pshow.repo.datamodel.content.definition.DataType.Type;
import org.pshow.repo.datamodel.content.definition.DataTypeUnSupportExeception;

/**
 * @author roy
 * 
 */
public class PropertyValue {

    private Serializable value;
    private Type         type;
    
    public PropertyValue(PropertyModel pm){
        this.type = Type.valueOf(pm.getActualType());
        switch (type) {
            case ANY:
            case CONTENT:
            case DATE:
            case DATETIME:
                this.value = pm.getSerializableValue();
                break;
            case TEXT:
                this.value = pm.getStringValue();
                break;
            case INT:
                this.value = pm.getLongValue();
                break;
            case LONG:
                this.value = pm.getLongValue();
                break;
            case FLOAT:
                this.value = pm.getFloatValue();
                break;
            case DOUBLE:
                this.value = pm.getDoubleValue();
                break;
            case BOOLEAN:
                this.value = pm.getBooleanValue();
                break;
        }
    }

    public PropertyValue(Serializable value) throws DataTypeUnSupportExeception {
        this.value = value;
        this.type = Type.getObjectType(this.value);
    }

    public Serializable getValue() {
        return value;
    }

    public Type getType() {
        return this.type;
    }

    public int getActualType() {
        return type.getActualType();
    }

    public String getTextValue() {
        if (this.type == Type.TEXT) {
            return (String) value;
        }
        return null;
    }

    public Long getLongValue() {
        if (this.type == Type.LONG) {
            return Long.valueOf(value.toString());
        }
        return null;
    }

    public Integer getIntValue() {
        if (this.type == Type.INT) {
            return Integer.valueOf(value.toString());
        }
        return null;
    }

    public Float getFloatValue() {
        if (this.type == Type.FLOAT) {
            return Float.valueOf(value.toString());
        }
        return null;
    }

    public Double getDoubleValue() {
        if (this.type == Type.DOUBLE) {
            return Double.valueOf(value.toString());
        }
        return null;
    }

    public Date getDateValue() {
        if (this.type == Type.DATE) {
            return (Date) value;
        }
        return null;
    }

    public Date getDateTimeValue() {
        if (this.type == Type.DATETIME) {
            return (Date) value;
        }
        return null;
    }

    public Boolean getBooleanValue() {
        if (this.type == Type.BOOLEAN) {
            return Boolean.valueOf(value.toString());
        }
        return null;
    }

}
