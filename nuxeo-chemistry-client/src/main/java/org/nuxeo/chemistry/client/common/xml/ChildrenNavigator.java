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
package org.nuxeo.chemistry.client.common.xml;

import javax.xml.stream.XMLStreamException;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 *
 */
public class ChildrenNavigator {

    protected StaxReader sr;
    protected int depth;

    public ChildrenNavigator(StaxReader sr) throws XMLStreamException {
        this.sr = sr;
        int tok = sr.getEventType();
        if (tok == StaxReader.END_ELEMENT) {
            depth = sr.depth+1;
        } else if (tok == StaxReader.START_ELEMENT) {
            depth = sr.depth;   
        } else { 
            throw new XMLStreamException("Ilegal state: current event must be START_ELEMENT or END_ELEMENT");
        }
    }

    public ChildrenNavigator(StaxReader sr, int depth) {
        this.sr = sr;
        this.depth = depth;
    }
    
    public boolean next() throws XMLStreamException {
        return sr.fwdSibling(depth);
    }
    
}
