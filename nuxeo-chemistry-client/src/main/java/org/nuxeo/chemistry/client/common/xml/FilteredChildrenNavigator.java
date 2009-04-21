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
public class FilteredChildrenNavigator extends ChildrenNavigator {

    public FilteredChildrenNavigator(StaxReader sr) throws XMLStreamException {
        super (sr);
    }

    public FilteredChildrenNavigator(StaxReader sr, int depth) {
        super (sr, depth);
    }
    
    protected boolean accept() {
        return true;
    }
    
    @Override
    public boolean next() throws XMLStreamException {
        while (super.next()) {
            if (accept()) return true;
        }
        return false;
    }
}
