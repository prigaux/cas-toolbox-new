/**
 *  Copyright 2007 Rutgers, the State University of New Jersey
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *      
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.esupportail.cas.statistics.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.inspektr.audit.AuditTrailManager;
import com.github.inspektr.audit.AuditActionContext;

public final class InMemoryStatisticManager implements AuditTrailManager {
	public static boolean enabled=false;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static Map<String, Integer> statsVal = Collections.synchronizedMap(new HashMap<String, Integer>());
	
    public void record(final AuditActionContext auditActionContext) {
    	if(enabled) {
	    	String what = auditActionContext.getActionPerformed();
			
	    	if(!statsVal.containsKey(what)) {
				statsVal.put(what , 0);					
			}
	    	int previousVal = statsVal.get(what);
			int newVal = previousVal + 1;
			statsVal.put(what , newVal);	
    	}
    }
    
    public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
    
    public static boolean isEnabled() {
		return enabled;
	}
}

/*import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inspektr.statistics.StatisticManager;
import org.inspektr.statistics.StatisticActionContext;

public final class InMemoryStatisticManager implements StatisticManager {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	public static Map<String, Integer> statsVal = Collections.synchronizedMap(new HashMap<String, Integer>());
	
	public final void recalculate(final StatisticActionContext statisticActionContext) {
		StatisticActionContext context = statisticActionContext;
		String what = context.getWhat();
		
		if(!statsVal.containsKey(what)) {
			statsVal.put(what , 0);					
		}
		int previousVal = statsVal.get(what);
		int newVal = previousVal + 1;
		statsVal.put(what , newVal);				
	}		
}
*/