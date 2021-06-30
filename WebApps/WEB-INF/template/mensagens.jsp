


<c:if test="${not empty listaMensagens}">
	<br/>
	<c:forEach items="${listaMensagens}" var="msg">		
		<c:choose>
	         
	         <c:when test = "${msg.error}">	            
	            <div class="alert alert-danger" role="alert">${msg.mensagem}</div>
	         </c:when>
	         
	         <c:when test = "${msg.success}">	            
	            <div class="alert alert-success" role="alert">${msg.mensagem}</div>
	         </c:when>
	         
	         <c:when test = "${msg.warning}">	            
	            <div class="alert alert-warning" role="alert">${msg.mensagem}</div>
	         </c:when>
	         	         
	         <c:otherwise>
	            <div class="alert alert-info" role="alert">${msg}</div>
	         </c:otherwise>
	         
	      </c:choose>	
	</c:forEach>
</c:if>