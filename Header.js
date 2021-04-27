import React, { Component } from 'react'
import { Anchor, Menu } from 'antd';
import './Add.css';
import axios from 'axios';
import M from 'materialize-css'

export default class Header extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: 0,
      supervisorId: 0,
      foodexpense:0,
      travelexpense:0,
      otherexpense: 0,
      foodBill: "",
      travelBill: "",
      otherBill: "",
      dateOfReimburse: '',
      viewRe: []
    };
  }
  myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({[nam]: val});
  }
  approve = (e,r) =>{
   
    axios.put("http://localhost:8082/reimbursementapi/all/supervisorStatus",{
        reimburceId:e.reimburceId,
        supervisorStatus:document.getElementById('approve').value
    })
}
disApprove = (e,r) =>{
    axios.put("http://localhost:8082/reimbursementapi/all/supervisorStatus",{
        reimburceId:e.reimburceId,
        supervisorStatus:document.getElementById('disapprove').value
    })
}

  
  viewReimburs = (event) => {
    event.preventDefault();
    document.getElementById("view").style.display = "block";
    axios.get("http://localhost:8082//reimbursementapi/all/totalReimburse"+"/"+2)
      .then(res => {
        console.log("hello");
        console.log(res.data)
      
        this.setState({ viewRe: res.data });
         console.log(this.state.viewRe);
      })
    .catch(err => console.log(err))
  }


    render() {
      const but = {
        position:'relative',
        left:'60%',
      };

      return (
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
              <button type="button" style={but} class="btn btn-dark">LogOut</button>

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
                            <i className="far fa-circle nav-icon" />
                            <p onClick={this.viewReimburs}>View Reimbursement</p>
                          </a>
                        </li>
                      </ul>
                    </li>   
                  </ul>
                </nav>
               </div>
              </aside>
            </div>

          
{/* *************************View Reimbursement */}
          
        <div class="card" id="view" style={{display:"none", marginBottom:"20px"}}>           
              {
                this.state.viewRe.map((r, id) => (
                <div className="card-body" style={{borderBottom:"2px solid gray"}}>
                    <p style={{fontWeight:"800"}} scope="col">Employee Id: </p><p>{r.employeeId}</p>
                    <p style={{fontWeight:"800"}} scope="col">Supervisor Id:  </p><p>{r.supervisorId}</p>
                    <p style={{fontWeight:"800"}} scope="col">Food Expense:  </p><p>{r.foodExpense}</p>
                    <p style={{fontWeight:"800"}} scope="col">Travel Expense:  </p><p>{r.travelExpense}</p>
                    <p style={{ fontWeight:"800"}} scope="col">Other Expense:  </p><p>{r.otherExpenss}</p>
                    <p style={{fontWeight:"800"}} scope="col">Total Expense:  </p><p>{r.totalExpense}</p>
                    <p style={{fontWeight:"800"}} scope="col">Supervisor Status:  </p><p>{r.supervisorStatus}</p><button value="approved" id="approve" onClick= { ()=> this.approve(r)} type="button" class="btn btn-outline-warning">Approved</button>{<button type="button" value="disApproved" id="disapprove" onClick= { ()=> this.disApprove(r)} class="btn btn-outline-warning">Disapproved</button>}
                    <p style={{fontWeight:"800"}} scope="col">Accountant Status:  </p><p>{r.accontantStatus}</p>
                    <p style={{fontWeight:"800"}} scope="col">Reimbursement Date:  </p><p>{r.dateOfReimburse}</p>
                    <p style={{fontWeight:"800"}} scope="col">Food Bill:  </p><p>{r.foodBill}</p>
                    <p style={{fontWeight:"800"}} scope="col">Travel Bill:  </p><p>{r.travelBill}</p>
                    <p style={{fontWeight:"800"}} scope="col">Other Bill:  </p><p>{r.otherBill}</p>
                </div>   
                ))
              }
          </div>

          </div>
        )
    }
}