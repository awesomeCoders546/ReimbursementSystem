import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Table from 'react-bootstrap/Table'
import './admindashByAdy.css'
import "datatables.net-dt/js/dataTables.dataTables"
import "datatables.net-dt/css/jquery.dataTables.min.css"
import $ from 'jquery'

export default function AttendanceSidebarContent() {
   
	useEffect(() => {
        if (localStorage.getItem("adminId") == null) {
            // window.location.replace("/") change
          }
    }, [])
      const logout = (e) => {
        e.preventDefault();
        localStorage.removeItem("adminId");
        // window.location.replace("/"); change
      }
	const [viewAttend, setViewAttend] = useState([]);
	const [Test, SetTest] = useState({});
	const data=useState([])
	const [attendenceId,setAttendenceId]=useState(0);
	const myObject=({});

	// const [state, setstate] = useState({});

	useEffect(() => {
			axios.get("http://localhost:8081/get")
				.then(res => {
					console.log(res.data);
				  setViewAttend(res.data)
				  SetTest(res.JSON)
				  console.log(res.data);

				
			  })
			.catch(err => console.log(err))
	}, [])

	useEffect(() => {
console.log(Test);
		setTimeout(function () {
			
			$('#example').dataTable({
				
	
			});


		}, 1000);
	}, [])

	
	
    return (
		<div  style={{ height: "100vh"}}>
		{/* <Table className="attendancetable" striped size="sm" style={{fontFamily: 'Nunito',fontSize:'15px',textAlign:'center'}}>
				<thead style={{backgroundColor:"#e6e6ff"}}>
					<tr>
					<th scope="col">Attendence Id</th>
					<th scope="col">Employee Id</th>
					<th scope="col">Name</th>
					<th scope="col">Status</th>
					<th scope="col">LogHours</th>
					<th scope="col">Login Time</th>
					<th scope="col">Logout Time</th>
					</tr>
				</thead>
				{viewAttend.map(attend => (
							<tbody>
								<tr>
								<td>{attend.attendenceId}</td>
								<td>{attend.employeeId}</td>
								<td>{attend.employeeName}</td>
								<td>{attend.status}</td>
								<td>{attend.logHours}</td>
								<td>{attend.loginTime}</td>
								<td>{attend.logoutTime}</td>
								</tr>
							</tbody>
							))
							}
				
				</Table> */}
<div class="col-lg-12" style={{marginTop:'5%'}}>
              <div class="card mb-4">
                <div class="table-responsive p-3">
<table id="example" class="table table-striped table-bordered" style={{width:'100%',marginLeft:'10%'}}>
<thead>
					<tr>
					<th scope="col">Attendence Id</th>
					<th scope="col">Employee Id</th>
					<th scope="col">Name</th>
					<th scope="col">Status</th>
					<th scope="col">LogHours</th>
					<th scope="col">Login Time</th>
					<th scope="col">Logout Time</th>
					</tr>
				</thead>
		{viewAttend.map(attend => (
							<tbody>
								<tr>
								<td>{attend.attendenceId}</td>
								<td>{attend.employeeId}</td>
								<td>{attend.employeeName}</td>
								<td>{attend.status}</td>
								<td>{attend.logHours}</td>
								<td>{attend.loginTime}</td>
								<td>{attend.logoutTime}</td>
								</tr>
							</tbody>
							))
							}
				</table>			

		</div>
	</div>
</div>
          </div>
		  )
}
