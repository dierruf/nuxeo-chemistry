/*
 * Copyright 2009 Nuxeo SA <http://nuxeo.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors:
 *     Florent Guillaume
 */
package org.nuxeo.ecm.core.chemistry.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.ObjectEntry;
import org.apache.chemistry.PropertyDefinition;
import org.apache.chemistry.Type;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.DocumentModel;

public class NuxeoObjectEntry implements ObjectEntry {

    private final DocumentModel doc;

    private final NuxeoConnection connection;

    private Type type;

    protected NuxeoObjectEntry(DocumentModel doc, NuxeoConnection connection) {
        this.doc = doc;
        this.connection = connection;
        type = connection.repository.getType(doc.getType());
    }

    public String getId() {
        return doc.getId();
    }

    public String getTypeId() {
        return doc.getType();
    }

    public Serializable getValue(String name) {
        try {
            // TODO avoid constructing property object
            return NuxeoProperty.getProperty(doc, type, name,
                    connection.session).getValue();
        } catch (ClientException e) {
            throw new RuntimeException(e.toString(), e); // TODO
        }
    }

    public void setValue(String name, Serializable value) {
        try {
            // TODO avoid constructing property object
            NuxeoProperty.getProperty(doc, type, name, connection.session).setValue(
                    value);
        } catch (ClientException e) {
            throw new RuntimeException(e.toString(), e); // TODO
        }
    }

    public Map<String, Serializable> getValues() {
        Map<String, Serializable> values = new HashMap<String, Serializable>();
        for (PropertyDefinition propertyDefinition : type.getPropertyDefinitions()) {
            String name = propertyDefinition.getName();
            values.put(name, getValue(name));
        }
        return values;
    }

    public void setValues(Map<String, Serializable> values) {
        for (String name : values.keySet()) {
            setValue(name, values.get(name));
        }
    }

    public Collection<String> getAllowableActions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

    public Collection<ObjectEntry> getRelationships() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
