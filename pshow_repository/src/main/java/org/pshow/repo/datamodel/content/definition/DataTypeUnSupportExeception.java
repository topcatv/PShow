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
package org.pshow.repo.datamodel.content.definition;

import org.pshow.repo.service.TypeException;


/**
 * @author roy
 *
 */
public class DataTypeUnSupportExeception extends TypeException {
    private static final long serialVersionUID = -3306642210487678404L;

    public DataTypeUnSupportExeception() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DataTypeUnSupportExeception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public DataTypeUnSupportExeception(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public DataTypeUnSupportExeception(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public DataTypeUnSupportExeception(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
