<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java"
	import="com.pierceecom.model.BlogPost,
 java.util.ArrayList,
 java.util.List"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5.01 Transitional//EN"
   "http://www.w3.org/TR/html5/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blog Post</title>
<LINK id="css" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/Blog.css">
</head>
<BODY style="border: none; overflow-y: hidden"
  onload="showscroll()">
	<script language="javascript">
    function showscroll() {
        document.body.style.overflow = 'scroll';
    }
</script>
<script>
		function onEditClick(elmnt1,elmnt2,elmnt3,elmnt4) {
			if (elmnt2.value=="Edit") {
				elmnt1.disabled = false;
				elmnt2.value  = "Cancel";
				elmnt2.type="reset";
				elmnt3.style.visibility = "visible";
				
			} else {
				elmnt1.disabled = true;
				elmnt2.value = "Edit";
				elmnt3.style.visibility = "hidden";
			}

		}

	
	</script>
	<%
	 String event = (String)session.getAttribute("EVNT");
	if(event == null ) {
		session.setAttribute("EVNT", "initialise") ;
		event = "init";
	}%>
	

	<h2>
		BlogPost Application
		<%=event %></h2>
	<form method="GET" action="BlogClient">

		<br> Post Id <input name="MsgId" 
			Type="Text"> <input name="methodName" value="Get Message"
			Type="Submit">
</form>
<br><br>

<form method="POST" action="BlogClient">
<input name="id" value ="0" Type="hidden"  style="width:70px" >
<table><tr><td>
Post Title :</td><td> <input name="title"  Type="Text"  style="width:300px" ></td>
</tr>
<tr><td>
 Post:</td><td>	<textarea placeholder="Post your messages" rows="10" cols="200" name="message"  ></textarea>
	
 
	 <input name="methodName" value="Post"
			Type="Submit"></td></tr></table>

	</form>
	<br>
	<br>

	<%

	if (request.getAttribute("Status") != null && request.getAttribute("Status").equals("200") ){
		%>
<label> Success</label>
<%
	}
	BlogPost b1 = new BlogPost();
		List<BlogPost> list = new ArrayList<BlogPost>();
	String	ReturnType =(String) request.getAttribute("ReturnType");
	if(session.getAttribute("EVNT")!= "initialise"){
		if ( ReturnType != null && ReturnType.equals("BlogList") ){
			session.setAttribute("EVNT", "init") ;
			 list= (List<BlogPost>)request.getAttribute("BlogList");
		}
		else if(ReturnType != null &&  ReturnType.equals("BlogPost"))
		{
			session.setAttribute("EVNT", "init") ;
			 b1 =(BlogPost)request.getAttribute("BlogPost");
			 list.add(b1);
		}}

		for(int i = 0; i < list.size(); i++){
		BlogPost	b= list.get(i);
	%>
	<form method="POST" id="Blogpost" name = "Blog"  action="BlogClient">
	<table> <tr><td>Id:	</td>  <td>  <input id="bid"  name="bid" value=<%=b.getId()%> type ="text" readonly="readonly" style="width: 300px">
</td></tr>
<tr>	
<td>Post Title:</td><td>	<input id="btitle"  name="btitle" value=<%=b.getTitle()%> type ="text" readonly="readonly" style="width: 300px">
	</td></tr>
<tr>
<td>Post: </td>
<td><textarea rows="10" cols="200" name="bcontent" disabled=true ><%=b.getContent()%>
 </textarea>

		<input name="delete" value="Delete" Type="Submit">
	 <input	type="button" value="Edit" onclick="onEditClick(bcontent,this,saveButton,bid)" />
		<input name="saveButton" value="Save" Type="Submit" style="visibility: hidden"></td></tr>
		</table>
		<br>




	</form>
	<br>
	<%
		}
	%>


</body>

</html>
