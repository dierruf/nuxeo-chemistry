/*
 * (C) Copyright 2006-2007 Nuxeo SAS (http://nuxeo.com/) and contributors.
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
 *
 * $Id$
 */

package org.nuxeo.chemistry.shell.jline;

import java.util.List;

import jline.Completor;

import org.nuxeo.chemistry.shell.app.Console;
import org.nuxeo.chemistry.shell.app.Context;
import org.nuxeo.chemistry.shell.util.Path;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 *
 */
public class ContextItemCompletor implements Completor {

    protected void collectNames(String[] keys, String prefix, List candidates) {
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                key = key.replace(" ", "\\ ");
                candidates.add(key);
            }
        }
    }

    public int complete(String buffer, int cursor, List candidates) {
        if (buffer == null) {
            buffer = "";
        }

        Context ctx;
        Path path = new Path(buffer);
        String prefix = path.getLastSegment();
        if (prefix == null) {
            ctx = Console.getDefault().getApplication().getContext();
            prefix = "";
        } else if (path.segmentCount() == 1) {
            ctx = Console.getDefault().getApplication().getContext();
        } else {
            path = path.removeLastSegments(1);
            ctx = Console.getDefault().getApplication().resolveContext(path);
        }
        if (ctx != null) {
            collectNames(ctx.entries(), prefix, candidates);
            return buffer.length()-prefix.length();
        } else {
            return -1;
        }
    }

}