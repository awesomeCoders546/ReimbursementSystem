import axios from 'axios';
import React from 'react';
import Reimservice from '../service/Reimservice';
import { Anchor, Menu } from 'antd';
import '../Add.css';

class Reimcomponent extends React.Component{
constructor(props){
    super(props)
    this.state={
        accountReimburse:[]

    }
}
componentDidMount(){
    Reimservice.getReimburse().then((response)=>{
        console.log(response)
this.setState({accountReimburse : response.data})
console.log(this.state.accountReimburse);
    });
}
viewReim = (e) => {
e.preventDefault();
document.getElementById('ReimTable').style.display="block";
}

    approve = (e,r) =>{
        axios.put("http://localhost:8082/reimbursementapi/all/accountantStatus",{
            reimburceId:e.reimburceId,
            accontantStatus:document.getElementById('approve').value
        })
    }
    disApprove = (e,r) =>{
        axios.put("http://localhost:8082/reimbursementapi/all/accountantStatus",{
            reimburceId:e.reimburceId,
            accontantStatus:document.getElementById('disapprove').value
        })
    }
render()
{
    const but = {
        position:'relative',
        left:'80%',
      };

      return (
          <div>
            <div id="home">

{/* *************************Navbar */}

            <nav className="main-header navbar navbar-expand navbar-white">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <a className="nav-link" data-widget="pushmenu" href="#"><i className="fas fa-bars" /></a>
                </li>
                 <li className="nav-item d-none d-sm-inline-block">
                      <a href="#home" className="nav-link">Home</a>
                  </li>
              </ul>
              <button type="button" style={but} class="btn btn-outline-danger">Logout</button>

            </nav>

{/* *************************SideBar */}
           
            <div>
            <aside className="main-sidebar sidebar-dark-primary elevation-4">
              {/* Brand Logo */}
              <a className="brand-link">
                <span className="brand-text font-weight-light">Reimbursement Dashboard</span>
              </a>
              <div className="sidebar">

                <nav className="mt-2">
                  <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                
                    <li className="nav-item has-treeview menu-open">
                      <a href="#" className="nav-link active">
                        <i className="nav-icon fas fa-tachometer-alt" />
                        <p>
                        Reimbursement
                          <i className="right fas fa-angle-left" />
                        </p>
                      </a>
                      <ul className="nav nav-treeview">
                        <li className="nav-item">
                          <a className="nav-link">
                            <i className="fa fa-eye" />
                            <p onClick={this.viewReim}>View Reimbursement</p>
                          </a>
                        </li>
                        <li className="nav-item">
                          <a className="nav-link">
                            <i className="fa fa-eye" />
                            <p>View Attendance</p>
                          </a>
                        </li>
                      </ul>
                    </li>   
                  </ul>
                </nav>
               </div>
              </aside>
            </div>

</div>











{/* 
<div>
<div class="navigation">
  
  <a class="button" href="">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjsuJHg3adlr9yUA6h8V9CfCZ7LmYBV8Uz4w&usqp=CAU" />

<div class="logout">LOGOUT</div>

  </a>

</div>
                <input type="checkbox" id="check" />
    <label for="check">
        <i class="fas fa-bars" id="btn"></i>
        <i class="fas fa-times" id="cancel"></i>
    </label> 
<header>
    <form class="example" action="/action_page.php" >
        <input type="text" placeholder="Search.." name="search2"/>
        <button type="submit"><i class="fa fa-search"></i></button>
      </form>
      
</header>
    <div class="sidebar">

<header>
    Dashboard
</header>
<ul> 
    <li ><a  ><i class ="fa fa-home"></i>Home</a></li>
    <li ><a ><i class ="fa fa-eye"></i>View Attendance</a></li>
    <li ><a  onClick={this.viewReim}><i class ="fa fa-eye"></i>View Reimbursement</a></li>
     */}
{/* 
</ul>
    </div>
   
            </div> */}
                         

{/* <table className="table table-striped" id="ReimTable" style={{display:"none"}}>
    <thead>
        <tr>
            <td>Reimburse_id</td>
            <td>Accountant_status</td>
            <td>Date of Reimburse</td>
            <td>Employee_id</td>
            <td>Food Bill</td>
            <td>Food Expense</td>
            <td>Created_date</td>
            <td>Other Bill</td>
            <td>Other Expense</td>
            <td>Supervisor_Id</td>
            <td>Total Expense</td>
            <td>Travel Bill</td>
            <td>Travel Expense</td>
            <td>Actions</td>
        </tr>
    </thead>
    <tbody>
        {
            this.state.accountReimburse.map(
                reim=>(
            <tr key={reim.id}>

              <td >{reim.reimburceId}</td>
              <td >{reim.accontantStatus}</td>
        
              <td >{reim.dateOfReimburse}</td>
              <td >{reim.employeeId}</td>
              <td >{reim.foodBill}</td>
              <td >{reim.foodExpense}</td>
              <td >{reim.createdDate}</td>
              <td >{reim.otherBill}</td>

             <td >{reim.otherExpenss}</td>
             <td >{reim.supervisorId}</td>
             <td >{reim.totalExpense}</td>
             <td >{reim.travelBill}</td>
             <td >{reim.travelExpense}</td>
             <button type="button" class="btn btn-outline-warning">Update</button>

             
               

            </tr>   )
            )
            
            
        }
    </tbody>
</table> */}

<div class="card1" id="ReimTable" style={{display:"none", marginBottom:"20px"}}>           
              {
                this.state.accountReimburse.map((r, id) => (
                <div className="card-body" style={{borderBottom:"2px solid gray"}}>
                    <p style={{fontWeight:"800"}} scope="col">Reimburse_Id : </p><p>{r.reimburceId}</p>
                    <p style={{fontWeight:"800"}} scope="col">Accountant_status :  </p><p>{r.accontantStatus}<br/><button value="approved" id="approve" onClick= { ()=> this.approve(r)} type="button" class="btn btn-outline-warning">Approved</button>{<button type="button" value="disApproved" id="disapprove" onClick= { ()=> this.disApprove(r)} class="btn btn-outline-warning">Disapproved</button>}</p>
                    <p style={{fontWeight:"800"}} scope="col">Date of Reimburse  </p><p>{r.dateOfReimburse}</p>
                    <p style={{fontWeight:"800"}} scope="col">Employee Id:  </p><p>{r.employeeId}</p>
                    <p style={{fontWeight:"800"}} scope="col">Food Bill:  </p><p>{r.foodBill}</p>
                    <p style={{fontWeight:"800"}} scope="col">Food Expense:  </p><p>{r.foodExpense}</p>
                    <p style={{fontWeight:"800"}} scope="col">Created Date:  </p><p>{r.createdDate}</p>
                    <p style={{fontWeight:"800"}} scope="col">Other Bill:  </p><p>{r.otherBill}</p>
                    <p style={{ fontWeight:"800"}} scope="col">Other Expense:  </p><p>{r.otherExpenss}</p>
                    <p style={{fontWeight:"800"}} scope="col">Supervisor Id:  </p><p>{r.supervisorId}</p>
                    <p style={{fontWeight:"800"}} scope="col">Total Expense:  </p><p>{r.totalExpense}</p>
                    <p style={{fontWeight:"800"}} scope="col">Travel Bill:  </p><p>{r.travelBill}</p>
                    <p style={{fontWeight:"800"}} scope="col">Travel Expense:  </p><p>{r.travelExpense}</p>
                    {/* <p style={{fontWeight:"800"}} scope="col">Travel Expense:  </p><p>{<button type="button" class="btn btn-outline-warning">Edit Accountant Status</button>}</p> */}
                </div>   
                ))
              }
          </div>
          


        </div>
    );
}
}
export default Reimcomponent;