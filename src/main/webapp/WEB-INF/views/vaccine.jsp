<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="parts/meta.jsp"%>

<title>Vaccination Slots</title>
<%@ include file="parts/header.jsp"%>

<div class= "p-3">

<table class="table">
    <thead>
      <tr>
        <th scope="col">Center ID</th>
        <th scope="col">Name</th>   
        <th scope="col">Available Capacity</th>
        <th scope="col">Address</th>
        <th scope="col">BlockName</th>
        <th scope="col">District Name</th>  
        <th scope="col">State Name</th> 
        <th scope="col">Fee</th>
        <th scope="col">Vaccine</th>
        <th scope="col">Slots</th>
        <th scope="col">From</th>
        <th scope="col">To</th>
        <th scope="col">Minimum Age Limit</th>
      </tr>
    </thead>
    <tbody>
    
        <c:if test="${not empty vaccines}">

                <c:forEach var="session" items="${vaccines}">
                    <!-- <li>${session.centerId}</li> -->
                    <tr>
                        <td>${session.centerId}</td>
                        <td>${session.name}</td>
                        <td>${session.availableCapacity}</td>
                        <td>${session.address}</td>
                        <td>${session.blockName}</td>
                        <td>${session.districtName}</td>
                        <td>${session.stateName}</td>
                        <td>${session.fee}</td>
                        <td>${session.vaccine}</td>
                        <td>${session.slots}</td>
                        <td>${session.from}</td>
                        <td>${session.to}</td>
                        <td>${session.minAgeLimit}</td>
                      </tr>


                </c:forEach>

        </c:if>
      



    </tbody>
  </table>

  </div>

<%@ include file="parts/footer.jsp"%>

