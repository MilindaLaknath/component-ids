/*******************************************************************************
 * Copyright  (c) 2015-2017, WSO2.Telco Inc. (http://www.wso2telco.com) All Rights Reserved.
 *
 * WSO2.Telco Inc. licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.wso2telco.gsma.authenticators.attributeshare;

import com.wso2telco.core.dbutils.DBUtilException;
import com.wso2telco.gsma.authenticators.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.authentication.framework.context.AuthenticationContext;

import javax.naming.NamingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NormalSp extends AbstractAttributeShare {

    private static Log log = LogFactory.getLog(NormalSp.class);

    /**
     * @param context
     * @return
     * @throws DBUtilException
     * @throws NamingException
     */
    @Override
    public Map<String, String> getAttributeShareDetails(AuthenticationContext context) throws NamingException,
            DBUtilException {
        String displayScopes = "";
        String isDisplayScope = "false";
        String authenticationFlowStatus = "false";
        String isTncForNewUser;
        boolean isRegistering = false;

        Map<String, List<String>> attributeSet = getAttributeMap(context);
        Map<String, String> attributeShareDetails = new HashMap();
        if (context.getProperty(Constants.IS_REGISTERING) != null) {
            isRegistering = (boolean) context.getProperty(Constants.IS_REGISTERING);

        }

        if (!attributeSet.get(Constants.EXPLICIT_SCOPES).isEmpty()) {
            isDisplayScope = "true";
            displayScopes = Arrays.toString(attributeSet.get(Constants.EXPLICIT_SCOPES).toArray());
            log.debug("Found the explicit scopes to gt the consent" + displayScopes);
        }

        if (isRegistering) {
            isTncForNewUser = "true";
            attributeShareDetails.put(Constants.IS_TNC, isTncForNewUser);
        }
        context.setProperty(Constants.IS_CONSENTED, Constants.YES);

        attributeShareDetails.put(Constants.IS_DISPLAYSCOPE, isDisplayScope);
        attributeShareDetails.put(Constants.DISPLAY_SCOPES, displayScopes);
        attributeShareDetails.put(Constants.IS_AUNTHENTICATION_CONTINUE, authenticationFlowStatus);

        return attributeShareDetails;
    }
}
