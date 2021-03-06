<!--
 ~ Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 ~
 ~ WSO2 Inc. licenses this file to you under the Apache License,
 ~ Version 2.0 (the "License"); you may not use this file except
 ~ in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~    http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing,
 ~ software distributed under the License is distributed on an
 ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ~ KIND, either express or implied.  See the License for the
 ~ specific language governing permissions and limitations
 ~ under the License.
 -->
<%@ page import="org.wso2.carbon.identity.application.authentication.endpoint.util.CharacterEncoder"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

    String stat = CharacterEncoder.getSafeText(request.getParameter("status"));
    String statusMessage = CharacterEncoder.getSafeText(request.getParameter("statusMsg"));
    if(stat == null || statusMessage == null){
        stat = "Authentication Error !";
        statusMessage = "Something went wrong during the authentication process. Please try signing in again.";
    }
    session.invalidate();
%>
<style>
.info-box{
background-color:#EEF3F6;
border:1px solid #ABA7A7;
font-size:13px;
font-weight:bold;
margin-bottom:10px;
padding:10px;
}
</style>

<div id="middle">

	<div id="workArea">
		<div class="info-box">
			<%=stat%>
		</div>
		<table class="styledLeft">
			<tbody>
				<tr>
					<td><%=statusMessage%></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>




