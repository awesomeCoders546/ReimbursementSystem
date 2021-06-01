import axios from 'axios';
import React from 'react';
import Reimservice from '../../services/AccountantReim';
import { Layout } from 'antd';
import { Dialog, DialogContent, DialogTitle, Icon } from "@material-ui/core";
import HighlightOffIcon from '@material-ui/icons/HighlightOff'
import { Link } from 'react-router-dom'
import logo from '../logo1.png'

class AccountantDash extends React.Component{
constructor(props){
    super(props)
    this.state={
      accountReimburse:[],
      viewAttend: [],
      accountantAprR: [],
      accountantDisR: [],
      actionRequired: [],
      actionRequiredSingle: {},
      accountantSingleAppR: {},
      accountantSingleDisR: {},
      accountant1: false,
      accountant2: false,
      actionOpen: false,
    }
  }
  
  componentDidMount() {
    if (localStorage.getItem("accountantId") == null) {
      window.location.replace("/")
    }
  }

  logout = (e) => {
    e.preventDefault();
    localStorage.removeItem("accountantId");
    window.location.replace("/");
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
document.getElementById('reim').style.display="block";
document.getElementById("attend").style.display = "none";

}
    
viewAttend = (event) => {
  event.preventDefault();
  document.getElementById("attend").style.display = "block";
  document.getElementById("reim").style.display = "none";
  axios.get("http://localhost:8081/get")
    .then(res => {        
      this.setState({ viewAttend: res.data });
      console.log(this.state.viewAttend);
    })
  .catch(err => console.log(err))
}

    approve = (e,r) =>{
        axios.put("http://localhost:8081/accountantStatus",{
            reimburceId:e.reimburceId,
            accontantStatus:document.getElementById('approve').value
        })
    }
    disApprove = (e,r) =>{
        axios.put("http://localhost:8081/accountantStatus",{
            reimburceId:e.reimburceId,
            accontantStatus:document.getElementById('disapprove').value
        })
  }


  accountantApproved = (e) => {
    e.preventDefault();
    document.getElementById("View").style.display = "block"
    document.getElementById("disview").style.display = "none"
    document.getElementById("action").style.display = "none"

      axios
    .get(
      "http://localhost:8081/allAccountantApproved"
    )
      .then((res) => {
      
      this.setState({ accountantAprR: res.data });
    })
    .catch((err) => console.log(err));
  }

  removeApr = () => {
    document.getElementById("View").style.display = "none";
    document.getElementById("disview").style.display = "none";
    document.getElementById("action").style.display = "none";
  }
  
  accountantDisapproved = (e) => {
    e.preventDefault();
    document.getElementById("disview").style.display = "block"
    document.getElementById("View").style.display = "none"
    document.getElementById("action").style.display = "none"

    axios
      .get(
        "http://localhost:8081/allAccountantDisApproved"
      )
      .then((res) => {
        this.setState({ accountantDisR: res.data });
      })
      .catch((err) => console.log(err));
  }

  approvedByAcc = (e, r) => {
    e.preventDefault();
    
    this.setState({ accountant1: "true" });
    axios
        .get(
          "http://localhost:8081/reimburseById" +
          "/" +
          r.reimburceId
        )
        .then((res) => {
          this.setState({ accountantSingleAppR: res.data });
        })
        .catch((err) => console.log(err));
  } 
  disapprovedByAcc = (e, r) => {
    e.preventDefault();
    this.setState({accountant2: "true"});
    axios
    .get(
      "http://localhost:8081/reimburseById" +
      "/" +
      r.reimburceId
    )
    .then((res) => {
      this.setState({ accountantSingleDisR: res.data });
    })
    .catch((err) => console.log(err));
  }
  actionRequiredforR = (e, r) => {
    e.preventDefault();
    this.setState({actionOpen: "true"});
    axios
    .get(
      "http://localhost:8081/reimburseById" +
      "/" +
      r.reimburceId
    )
    .then((res) => {
      this.setState({ actionRequiredSingle: res.data });
    })
    .catch((err) => console.log(err));
  } 

  actionRequired = (e) => {
    e.preventDefault();
    document.getElementById("disview").style.display = "none"
    document.getElementById("View").style.display = "none"
    document.getElementById("action").style.display = "block"

        axios.get("http://localhost:8081/accountReimburse")
          .then(res => {
            this.setState({ actionRequired: res.data });
             console.log(this.state.viewRe);
          })
        .catch(err => console.log(err))
  }
render()
{

  const but = {
        position:'relative',
        left:'60%',
    };
  const logostyle = {
    height: "48px",
    marginLeft:"12px"
    }

      return (
          <div>
            <div id="home" style={{width:"100vw", height:"100%"}}>

{/* *************************Navbar */}

            <nav className="site-layout-background main-header navbar navbar-expand navbar-dark  w-100" style={{
              position: "fixed",
              display: "flex",
              top: "0",
              width: "90%",
              left: "0%",
              padding:"17px",
              backgroundColor: "#59c5ff"
            }}>
              <ul className="navbar-nav">
                <li className="nav-item">
                  <a className="nav-link text-dark" data-widget="pushmenu" href="#"><i className="fas fa-bars" style={{color:"white", fontSize:"20px"}} /></a>
                </li>
              </ul>
              <button type="button" style={but} class="btn btn-danger" onClick={this.logout}>Logout</button>
            </nav>

         

{/* *************************SideBar */}
           
            <div>
            <aside className="main-sidebar sidebar-dark-primary elevation-4" style={{backgroundColor:"black"}}>
                <a className="brand-link">
                  <img src={logo} style={logostyle} />
              </a>
              <div className="sidebar">

                <nav className="mt-2">
                  <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                
                    <li className="nav-item has-treeview menu-open">
                          <i className="mt-3 mb-2 nav-icon fas fa-tachometer-alt" style={{ color: "#ffa426", marginLeft:"18px" }} />
                          <Link className="link" style={{textDecoration:'none',padding:"10px",color:'#22b1ed'}} to="/accountantDash">Dashboard</Link>
                          <hr className="sidebar-divider" style={{borderTop:"2px solid #2e2e2e"}}/>
                        <div class="sidebar-heading" style={{color:'#b7b9cc',textAlign: 'left',padding:' 0 1rem',
                          fontWeight: '600',
                          fontSize: '.90rem',
                          marginBottom:"20px",
                          letterSpacing: '.13rem'}}>
                          Features
                        </div>
                        <ul className="nav nav-treeview">
                        <li className="nav-item">
                          <a className="nav-link">
                            <i className="fas fa-calculator " style={{color:"#ffa426",marginLeft:"8px",marginRight:"5px"}}/>
                            <p onClick={this.viewReim}>View Reimbursement</p>
                          </a>
                        </li>
                        <li className="nav-item">
                          <a className="nav-link">
                            <i className="fas fa-fw fa-chart-area" style={{color:"#ffa426",marginLeft:"8px",marginRight:"5px"}}/>
                            <p onClick={this.viewAttend }>View Attendance</p>
                          </a>
                          </li>
                          <li className="nav-item">
                          <a className="nav-link">
                          <i className="fas fa-clock" style={{color:"#ffa426",marginLeft:"8px",marginRight:"5px"}}/>
                            <Link to="/acctimesheet"><p>View TimeSheet</p></Link>
                          </a>
                        </li>
                      </ul>
                    </li>   
                    </ul>
                 <hr class="sidebar-divider"style={{borderTop:"2px solid #2e2e2e"}}/>
                  </nav>
               </div>
              </aside>
            </div>

</div>



{/* ***********View Attendance */}

<div id="attend" style={{marginLeft:"30%", marginTop:"6%", display:"none"}}>

            <div class="row">
            <div class="col-lg-12">
              <div class="card mb-4">
                <div class="table-responsive p-3">
                <table id="example" class="table table-striped table-bordered" style={{ width: "100%" }}>
                  <thead>
                      <tr>
                        <th>Attendence Id</th>
                        <th>Employee Id</th>
                        <th>Name</th>
                        <th>Status</th>
                        <th>LogHours</th>
                        <th>Login Time</th>
                        <th>Logout Time</th>
                      </tr>
                  </thead>
                  {
                          this.state.viewAttend.map(a => (
                            <tbody>
                            <tr>
                              <td>{a.attendenceId}</td>
                              <td>{a.employeeId}</td>
                              <td>{a.employeeName}</td>
                              <td>{a.status}</td>
                              <td>{a.logHours}</td>
                              <td>{a.loginTime}</td>
                              <td>{a.logoutTime}</td>
                            </tr>
                          </tbody>
                  ))          
               }         
                </table>
                </div>
              </div>
            </div>
          </div>
          </div>

          {/* *************************View options************ */}
            <div id="reim">
              <div  style={{display:"flex", marginTop:"6%", marginLeft:"19%"}}> 
                  <div
                      style={{
                        boxShadow: "4px 5px 8px 9px grey",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
    
                      }}
                      id="adminRe"
                    >
                      <p style={{ fontWeight: "600", backgroundColor: "white", fontSize: "30px" , padding: "9px", color:"grey" }}>Reimbursement</p>
                      <p style={{ fontWeight: "800", backgroundColor: "white", padding: "9px" }}>Approved by You</p>
                      <div onClick={(e) => this.accountantApproved(e)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", padding: "9px", backgroundColor: "black", border: "none", color: "white" }}>view</div>
                  </div>
                  <div
                      style={{
                        boxShadow: "0px 0px 10px 10px gray",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
                      }}
                    >
                      <p style={{ fontWeight: "600", backgroundColor: "white", fontSize: "30px" , padding: "9px", color:"grey" }}>Reimbursement</p>
                      <p style={{ fontWeight: "800", backgroundColor: "white", padding: "9px" }}>Disapproved by You</p>
                      <div onClick={(e) => this.accountantDisapproved(e)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", padding: "9px", backgroundColor: "black", border: "none", color: "white" }}>view</div>
                  </div>
                  <div
                      style={{
                        boxShadow: "0px 0px 10px 10px gray",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
                      }}
                    >
                      <p style={{ fontWeight: "600", backgroundColor: "white", fontSize: "30px" , padding: "9px", color:"grey" }}>Reimbursement</p>
                      <p style={{ fontWeight: "800", backgroundColor: "white", padding: "9px" }}>Action required</p>
                      <div onClick={(e) => this.actionRequired(e)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", padding: "9px", backgroundColor: "black", border: "none", color: "white" }}>view</div>
                  </div>
          </div>
          </div>
          
 {/* *************************View Reimbursements approved by supervisor */}

 <div className=" mt-2" id="View" style={{ display: "none", boxShadow: "0px 0px 10px 10px gray" ,  backgroundColor: "#ededed", marginLeft: "21%", width:"72%"}}>
          <div style={{display:"flex", justifyContent:"space-evenly"}}><h2 style={{ marginTop: "1%", color: "grey" }}>Reimbursements approved by you</h2>
          <Icon><HighlightOffIcon onClick={this.removeApr} style={{fontSize:"50px",cursor:"pointer"}}/></Icon></div><hr />
          <div style={{ display: "flex", marginLeft: "18%",  flexWrap: "wrap" }}>
            {
              this.state.accountantAprR.length != 0 ? (this.state.accountantAprR.map((r, id) => {
                return (
                  <>
                    <div
                      style={{
                        boxShadow: "0px 0px 10px 10px gray",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
                        borderRadius:"50%"
                      }}
                    >
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px" }}><p style={{ fontWeight: "800" }}>Reimbursement Id:   </p><p>{r.reimburceId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Employee Id:   </p><p>{r.employeeId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Supervisor status:   </p><p>{r.supervisorStatus}</p></div>
                      <div onClick={(e) => this.approvedByAcc(e, r)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", borderRadius: "11%", padding: "9px", backgroundColor: "#ffbe2b", marginTop: "4px", border: "none", color: "black" }}>view details</div>
                    </div>
                  </>
                )
              })):(<div style={{ textAlign: "center", marginLeft:"19%"}}><h5 style={{textAlign:"center", marginTop: "1%", color: "red" }}>No Reimbursement Found</h5></div>)
            }
          </div>
  </div>
         {/* ************************* Reimbursements disapproved by supervisor */}

         <div id="disview" className=" mt-2" style={{ display: "none", boxShadow: "0px 0px 10px 10px gray",  backgroundColor: "#ededed", marginLeft: "21%", width:"72%" }}>
         <div style={{display:"flex", justifyContent:"space-evenly"}}><h2 style={{ marginTop: "1%", color: "grey" }}>Reimbursements disapproved by you</h2>
         <Icon><HighlightOffIcon onClick={this.removeApr} style={{fontSize:"50px", cursor:"pointer"}}/></Icon></div><hr />
          <div style={{ display: "flex", marginLeft: "18%", flexWrap: "wrap" }}>
            {
              this.state.accountantDisR.length!=0? (this.state.accountantDisR.map((r, id) => {
                return (
                  <>
                    <div
                      // className="card2"
                      style={{
                        boxShadow: "0px 0px 10px 10px gray",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
                        borderRadius:"50%"
                      }}
                    >
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px" }}><p style={{ fontWeight: "800" }}>Reimbursement Id:   </p><p>{r.reimburceId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Employee Id:   </p><p>{r.employeeId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Supervisor status:   </p><p>{r.supervisorStatus}</p></div>
                      <div onClick={(e) => this.disapprovedByAcc(e, r)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", borderRadius: "11%", padding: "9px", backgroundColor: "#ffbe2b", marginTop: "4px", border: "none", color: "black" }}>view details</div>
                    </div>
                  </>
                )
              })):(<div style={{ textAlign: "center", marginLeft:"19%"}}><h5 style={{textAlign:"center", marginTop: "1%", color: "red" }}>No Reimbursement Found</h5></div>)
            }
          </div>
        </div>

 {/* *************************Reimbursements required action */}

 <div id="action" className=" mt-2" style={{ display: "none", boxShadow: "0px 0px 10px 10px gray",  backgroundColor: "#ededed", marginLeft: "21%", width:"72%" }}>
        <div style={{display:"flex", justifyContent:"space-evenly"}}><h2 style={{ marginTop: "1%", color: "grey" }}>Action Required</h2>
        <Icon><HighlightOffIcon onClick={this.removeApr} style={{fontSize:"50px", cursor:"pointer"}}/></Icon></div><hr />
          <div style={{ display: "flex", marginLeft: "18%", flexWrap: "wrap" }}>
            {
              this.state.actionRequired.length !=0 ?(this.state.actionRequired.map((r, id) => {
                return (
                  <>
                    <div
                      style={{
                        boxShadow: "0px 0px 10px 10px gray",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
                        borderRadius:"50%"
                      }}
                    >
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px" }}><p style={{ fontWeight: "800" }}>Reimbursement Id:   </p><p>{r.reimburceId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Employee Id:   </p><p>{r.employeeId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Supervisor status:   </p><p>{r.supervisorStatus}</p></div>
                      <div onClick={(e) => this.actionRequiredforR(e, r)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", borderRadius: "11%", padding: "9px", backgroundColor: "#ffbe2b", marginTop: "4px", border: "none", color: "black" }}>view details</div>
                    </div>
                  </>
                )
              })):(<div style={{ textAlign: "center", marginLeft:"19%"}}><h5 style={{textAlign:"center", marginTop: "1%", color: "red" }}>No Reimbursement Found</h5></div>)
            }
          </div>
          </div>

          {/* *************************Reimbursements by months

 <div id="action" className=" mt-2" style={{ display: "none", boxShadow: "0px 0px 10px 10px gray",  backgroundColor: "#ededed", marginLeft: "21%", width:"72%" }}>
        <div style={{display:"flex", justifyContent:"space-evenly"}}><h2 style={{ marginTop: "1%", color: "grey" }}>Action Required</h2>
        <Icon><HighlightOffIcon onClick={this.removeApr} style={{fontSize:"50px", cursor:"pointer"}}/></Icon></div><hr />
          <div style={{ display: "flex", marginLeft: "18%", flexWrap: "wrap" }}>
            {
              this.state.actionRequired.length !=0 ?(this.state.actionRequired.map((r, id) => {
                return (
                  <>
                    <div
                      style={{
                        boxShadow: "0px 0px 10px 10px gray",
                        backgroundColor: "black",
                        width: "280px",
                        margin: "34px",
                        borderRadius:"50%"
                      }}
                    >
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px" }}><p style={{ fontWeight: "800" }}>Reimbursement Id:   </p><p>{r.reimburceId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Employee Id:   </p><p>{r.employeeId}</p></div>
                      <div style={{ display: "flex", justifyContent: "space-between", backgroundColor: "white", borderRadius: "11%", padding: "9px", marginTop: "4px" }}><p style={{ fontWeight: "800" }}>Supervisor status:   </p><p>{r.supervisorStatus}</p></div>
                      <div onClick={(e) => this.actionRequiredforR(e, r)} id="Rbutton" style={{ cursor: "pointer", textAlign: "center", borderRadius: "11%", padding: "9px", backgroundColor: "#ffbe2b", marginTop: "4px", border: "none", color: "black" }}>view details</div>
                    </div>
                  </>
                )
              })):(<div style={{ textAlign: "center", marginLeft:"19%"}}><h5 style={{textAlign:"center", marginTop: "1%", color: "red" }}>No Reimbursement Found</h5></div>)
            }
          </div>
          </div> */}
          
  {/* *****************************view single reim****************     */}
          
           <Dialog
              open={this.state.accountant1}
              onClose={() => this.setState({accountant1: !this.state.accountant1})}
              className="dialog"
            >
              <DialogTitle>
                <div className="">
                  <h3>Reimbursement</h3><hr />
                </div>
              </DialogTitle>
              <DialogContent>
                  <div
                    classNamee="cardu mb-3 mt-3"
                    id="view"
                    style={{
                      boxShadow: "3px 4px 8px 9px gray",
                      backgroundColor: "white",
                      width: "550px",
                    }}
                  >
                    <div
                      className="card-body"
                      style={{ borderBottom: "9px solid gray" }}
                    >
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Reimburse_Id{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.reimburceId}</p>
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Employee Id{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.employeeId}</p>
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Supervisor Id{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.supervisorId}</p>
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Other Expense{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.otherExpenss}</p>
                        </div>
                        <div>
                          {" "}
                          <p style={{ fontWeight: "800" }} scope="col">
                            Food Expense{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.foodExpense}</p>
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Travel Expense{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.travelExpense}</p>
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Food Bill
                                </p>
                          <img
                            width="150"
                            height="150"
                            className="img-fluid"
                            src={`data:image/png;base64, ${this.state.accountantSingleAppR.foodBill}`}
                          />
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Other Bill{" "}
                          </p>
                          <img
                            width="150"
                            height="150"
                            className="img-fluid"
                            src={`data:image/png;base64, ${this.state.accountantSingleAppR.otherBill}`}
                          />
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Travel Bill{" "}
                          </p>
                          <img
                            width="150"
                            height="150"
                            className="img-fluid"
                            src={`data:image/png;base64, ${this.state.accountantSingleAppR.travelBill}`}
                          />
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Supervisor status{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.supervisorStatus}</p>
                        </div>
                        <div style={{ marginLeft: "64px" }}>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Accountant status{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.accontantStatus}</p>
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Created Date{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.createdDate}</p>
                        </div>
                        <div style={{ marginLeft: "95px" }}>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Date of Reimburse{" "}
                          </p>
                          <p>{this.state.accountantSingleAppR.dateOfReimburse}</p>
                        </div>
                      </div>
                    </div>
                  </div>
              </DialogContent>
        </Dialog>
        <Dialog
              open={this.state.accountant2}
              onClose={() => this.setState({accountant2: !this.state.accountant2})}
              className="dialog"
            >
              <DialogTitle>
                <div className="">
                  <h3>Reimbursement</h3><hr />
                </div>
              </DialogTitle>
              <DialogContent>
                  <div
                    classNamee="cardu mb-3 mt-3"
                    id="view"
                    style={{
                      boxShadow: "3px 4px 8px 9px gray",
                      backgroundColor: "white",
                      width: "550px",
                    }}
                  >
                    <div
                      className="card-body"
                      style={{ borderBottom: "9px solid gray" }}
                    >
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Reimburse_Id{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.reimburceId}</p>
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Employee Id{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.employeeId}</p>
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Supervisor Id{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.supervisorId}</p>
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Other Expense{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.otherExpenss}</p>
                        </div>
                        <div>
                          {" "}
                          <p style={{ fontWeight: "800" }} scope="col">
                            Food Expense{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.foodExpense}</p>
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Travel Expense{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.travelExpense}</p>
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Food Bill
                                </p>
                          <img
                            width="150"
                            height="150"
                            className="img-fluid"
                            src={`data:image/png;base64, ${this.state.accountantSingleDisR.foodBill}`}
                          />
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Other Bill{" "}
                          </p>
                          <img
                            width="150"
                            height="150"
                            className="img-fluid"
                            src={`data:image/png;base64, ${this.state.accountantSingleDisR.otherBill}`}
                          />
                        </div>
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Travel Bill{" "}
                          </p>
                          <img
                            width="150"
                            height="150"
                            className="img-fluid"
                            src={`data:image/png;base64, ${this.state.accountantSingleDisR.travelBill}`}
                          />
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Supervisor status{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.supervisorStatus}</p>
                        </div>
                        <div style={{ marginLeft: "64px" }}>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Accountant status{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.accontantStatus}</p>
                        </div>
                      </div>
                      <div
                        style={{
                          display: "flex",
                          padding: "6px",
                          border: "1px solid lightgrey",
                        }}
                      >
                        <div>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Created Date{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.createdDate}</p>
                        </div>
                        <div style={{ marginLeft: "95px" }}>
                          <p style={{ fontWeight: "800" }} scope="col">
                            Date of Reimburse{" "}
                          </p>
                          <p>{this.state.accountantSingleDisR.dateOfReimburse}</p>
                        </div>
                      </div>
                    </div>
                  </div>
              </DialogContent>
          </Dialog>
          <Dialog
              open={this.state.actionOpen}
              onClose={() => this.setState({actionOpen: !this.state.actionOpen})}
              className="dialog"
            >
              <DialogTitle>
                <div className="">
                  <h3>Reimbursement</h3><hr />
                </div>
              </DialogTitle>
              <DialogContent>
              <div class="cardu" id="action" className="cardu mb-3 mt-3"
                    
                    style={{
                      boxShadow: "3px 4px 8px 9px gray",
                      backgroundColor: "white",
                      width: "550px",
                    }}>           
                   <div className="card-body" style={{borderBottom:"9px solid gray"}}>
                    <div style={{display:"flex", padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Reimburse_Id  </p><p>{this.state.actionRequiredSingle.reimburceId}</p></div>
                    <div style={{ marginLeft:"93px" }}><p style={{fontWeight:"800"}} scope="col">Employee Id  </p><p>{this.state.actionRequiredSingle.employeeId}</p></div></div>
                    <div style={{display:"flex", justifyContent:"space-between",padding:"6px", border:"1px solid lightgrey"}}><div> <p style={{fontWeight:"800"}} scope="col">Food Expense  </p><p>{this.state.actionRequiredSingle.foodExpense}</p></div>
                    <div><p style={{fontWeight:"800"}} scope="col">Food Bill  </p><img
                        width="150"
                        height="150"
                        className="img-fluid"
                        src={`data:image/png;base64, ${this.state.actionRequiredSingle.foodBill}`}
                      /></div></div>
                    <div style={{display:"flex", justifyContent:"space-between",padding:"6px", border:"1px solid lightgrey"}}><div> <p style={{ fontWeight:"800"}} scope="col">Other Expense  </p><p>{this.state.actionRequiredSingle.otherExpenss}</p></div>
                    <div><p style={{fontWeight:"800"}} scope="col">Other Bill  </p><img
                        width="150"
                        height="150"
                        className="img-fluid"
                        src={`data:image/png;base64, ${this.state.actionRequiredSingle.otherBill}`}
                      /></div></div>
                    <div style={{display:"flex", justifyContent:"space-between",padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Travel Expense </p><p>{this.state.actionRequiredSingle.travelExpense}</p></div>
                    <div><p style={{ fontWeight: "800" }} scope="col">Travel Bill </p><img
                        width="150"
                        height="150"
                        className="img-fluid"
                        src={`data:image/png;base64, ${this.state.actionRequiredSingle.travelBill}`}
                      /></div></div>
                    <div style={{display:"flex",padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Supervisor status </p><p>{this.state.actionRequiredSingle.supervisorStatus}</p></div>
                    <div style={{ marginLeft: "64px" }}><p style={{ fontWeight: "800" }} scope="col">Supervisor Id </p><p>{this.state.actionRequiredSingle.supervisorId}</p></div></div>
                    <div style={{display:"flex",justifyContent:"space-between", padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Your status</p><p>{this.state.actionRequiredSingle.accontantStatus}</p></div><button value="approved" id="approve" onClick= { ()=> this.approve(this.state.actionRequiredSingle)} type="button" class="btn btn-dark">Approved</button>{<button type="button" value="disApproved" id="disapprove" onClick= { ()=> this.disApprove(this.state.actionRequiredSingle)} class="btn btn-dark">Disapproved</button>}</div>
                    <div style={{display:"flex",padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Created Date </p><p>{this.state.actionRequiredSingle.createdDate}</p></div>
                    <div style={{ marginLeft:"95px" }}><p style={{fontWeight:"800"}} scope="col">Date of Reimburse  </p><p>{this.state.actionRequiredSingle.dateOfReimburse}</p></div></div>
                </div>   
              </div> 
              </DialogContent>
          </Dialog>
          

          

        {/* *************************View Reimbursement
          
          <div className="cardu" id="view" style={{ boxShadow: "3px 4px 8px 9px gray",backgroundColor:"white", marginLeft:"30%", marginTop:"5%",width:"550px"}}>           
              {
                this.state.accountReimburse.map((r, id) => (
                <div className="card-body" style={{borderBottom:"9px solid gray"}}>
                    <div style={{display:"flex", padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Reimburse_Id  </p><p>{r.reimburceId}</p></div>
                    <div style={{ marginLeft:"93px" }}><p style={{fontWeight:"800"}} scope="col">Employee Id  </p><p>{r.employeeId}</p></div></div>
                    <div style={{display:"flex", justifyContent:"space-between",padding:"6px", border:"1px solid lightgrey"}}><div> <p style={{fontWeight:"800"}} scope="col">Food Expense  </p><p>{r.foodExpense}</p></div>
                    <div><p style={{fontWeight:"800"}} scope="col">Food Bill  </p><img
                        width="150"
                        height="150"
                        className="img-fluid"
                        src={`data:image/png;base64, ${r.foodBill}`}
                      /></div></div>
                    <div style={{display:"flex", justifyContent:"space-between",padding:"6px", border:"1px solid lightgrey"}}><div> <p style={{ fontWeight:"800"}} scope="col">Other Expense  </p><p>{r.otherExpenss}</p></div>
                    <div><p style={{fontWeight:"800"}} scope="col">Other Bill  </p><img
                        width="150"
                        height="150"
                        className="img-fluid"
                        src={`data:image/png;base64, ${r.otherBill}`}
                      /></div></div>
                    <div style={{display:"flex", justifyContent:"space-between",padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Travel Expense </p><p>{r.travelExpense}</p></div>
                    <div><p style={{ fontWeight: "800" }} scope="col">Travel Bill </p><img
                        width="150"
                        height="150"
                        className="img-fluid"
                        src={`data:image/png;base64, ${r.travelBill}`}
                      /></div></div>
                    <div style={{display:"flex",padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Supervisor status </p><p>{r.supervisorStatus}</p></div>
                    <div style={{ marginLeft: "64px" }}><p style={{ fontWeight: "800" }} scope="col">Supervisor Id </p><p>{r.supervisorId}</p></div></div>
                    <div style={{display:"flex",justifyContent:"space-between", padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Accountant_status</p><p>{r.accontantStatus}</p></div><button value="approved" id="approve" onClick= { ()=> this.approve(r)} type="button" class="btn btn-dark">Approved</button>{<button type="button" value="disApproved" id="disapprove" onClick= { ()=> this.disApprove(r)} class="btn btn-dark">Disapproved</button>}</div>
                    <div style={{display:"flex",padding:"6px", border:"1px solid lightgrey"}}><div><p style={{fontWeight:"800"}} scope="col">Created Date </p><p>{r.createdDate}</p></div>
                    <div style={{ marginLeft:"95px" }}><p style={{fontWeight:"800"}} scope="col">Date of Reimburse  </p><p>{r.dateOfReimburse}</p></div></div>
                </div>   
                ))
              }
          </div> */}
        </div>
    );
}
}
export default AccountantDash;