  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>Edit Book</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list">Book List</g:link></span>
            <span class="menuButton"><g:link class="create" action="create">New Book</g:link></span>
        </div>
        <div class="body">
            <h1>Edit Book</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${book}">
            <div class="errors">
                <g:renderErrors bean="${book}" as="list" />
            </div>
            </g:hasErrors>
            <g:form controller="book" method="post" >
                <input type="hidden" name="id" value="${book?.id}" />
                <div class="dialog">
                    <table>
                        <tbody>
													<tr class='prop'>
															<td valign='top' class='name'> 
																<label for='name'>Name:</label>
															</td>
															<td valign='top' class='value ${hasErrors(bean:book,field:'name','errors')}'>
																<input type="text" id='name' name='name' value="${fieldValue(bean:book,field:'name')}"/>
															</td>
													</tr> 
													<tr class='prop'>
															<td valign='top' class='name'> 
																<label for='tags'>Tags:</label>
															</td>
															<td valign='top' class='value ${hasErrors(bean:book,field:'tags','errors')}'>
																<input type="text" id='tags' name='tags' value="${fieldValue(bean:book,field:'tags')}"/>
															</td>
													</tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="Update" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="Delete" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
