import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Table from 'react-bootstrap/Table'
import './admindashByAdy.css'
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
	const data=useState([])
	const [attendenceId,setAttendenceId]=useState(0);

	// const [state, setstate] = useState({});

	useEffect(() => {
			axios.get("http://localhost:8081/get")
				.then(res => {
					console.log(res.data);
				  setViewAttend(res.data)
				  console.log(res.data);
				 
				
			  })
			.catch(err => console.log(err))
	}, [])


	
	
    return (
		<div  style={{ height: "60vh"}}>
		<Table className="attendancetable" striped size="sm" style={{fontFamily: 'Nunito',fontSize:'15px',textAlign:'center'}}>
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
				
				</Table>



		  
          </div>
		  )
}
