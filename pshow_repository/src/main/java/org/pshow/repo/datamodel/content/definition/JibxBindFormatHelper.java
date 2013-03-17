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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JibxBindFormatHelper
{
   
    /**
     * Convert XML date (of the form yyyy-MM-dd) to Date 
     * 
     * @param date  the xml representation of the date
     * @return  the date
     * @throws ParseException
     */
    public static Date deserialiseDate(String date)
        throws ParseException
    {
        Date xmlDate = null;
        if (date != null)
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            xmlDate = df.parse(date);
        }
        return xmlDate;
    }

    
    /**
     * Convert date to XML date (of the form yyyy-MM-dd)
     * 
     * @param date  the date
     * @return  the xml representation of the date
     */
    public static String serialiseDate(Date date)
    {
        String xmlDate = null;
        if (date != null)
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            xmlDate = df.format(date);
        }
        return xmlDate;
    }
    
    public static Boolean deserialiseBoolean(String booleanString) throws ParseException
    {
        Boolean bool = null;
        if((booleanString != null) && booleanString.length() > 0)
        {
            bool = Boolean.valueOf(booleanString);
        }
        return bool;
    }
    
    public static String serialiseBoolean(Boolean bool)
    {
        String booleanString = null;
        if (bool != null)
        {
            booleanString = bool.toString();
        }
        return booleanString;
    }
}