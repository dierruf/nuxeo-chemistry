/*
 * (C) Copyright 2006-2008 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     bstefanescu
 */
package org.nuxeo.ecm.webengine.cmis.util;

import java.io.OutputStream;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 *
 */
public interface EntryAdapter<T> {

    public void writeHeaders(Request req, T entry) throws Exception;
    
    public void writeEntry(Request req, T entry, OutputStream out) throws Exception;
    
    public T getEntry(Request req) throws Exception;

    public T putEntry(Request req) throws Exception;
    
    public T postEntry(Request req) throws Exception;
    
    public boolean deleteEntry(Request req) throws Exception;
    
    public int headEntry(Request req) throws Exception;
    
}
