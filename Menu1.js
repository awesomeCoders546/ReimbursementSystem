// import React, { Component } from 'react'

// export default class Menu1 extends Component {
//     render() {
//         return (
//             <aside class="main-sidebar sidebar-dark-primary elevation-4">
//               <a href="index3.html" class="brand-link">
                
//                 <span class="brand-text font-weight-light">Reimbursement Dashboard</span>
//               </a>
//               <div class="sidebar">
//                 <nav class="mt-2">
//                   <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu">
//                     <li class="nav-item has-treeview menu-open">
//                       <a href="#" class="nav-link active">
//                         <i class="nav-icon fas fa-tachometer-alt"></i>
//                         <p>
//                             Starter Pages
//                           <i class="right fas fa-angle-left"></i>
//                         </p>
//                       </a>
//                       <ul class="">
//                         <li class="nav-item">
//                           <a href="#" class="nav-link">
//                             <i class="far fa-circle nav-icon"></i>
//                             <p>Add Reimbursement</p>
//                           </a>
//                         </li>
//                         <li class="nav-item">
//                           <a href="#" class="nav-link">
//                             <i class="far fa-circle nav-icon"></i>
//                             <p>View Reimbursement</p>
//                           </a>
//                         </li>
//                         <li class="nav-item">
//                           <a href="#" class="nav-link">
//                             <i class="far fa-circle nav-icon"></i>
//                             <p>View Attendence</p>
//                           </a>
//                         </li>
//                         <li class="nav-item">
//                           <a href="#" class="nav-link">
//                             <i class="far fa-circle nav-icon"></i>
//                             <p>View Reimbursement</p>
//                           </a>
//                         </li>
//                       </ul>
//                     </li>
                   
//                   </ul>
//                 </nav>
//               </div>
              
// ]            </aside>
//         )
//     }
// }
import React, { Component } from 'react'
import { Anchor } from 'antd';

const { Link } = Anchor;
export default class Menu extends Component {
    render() {
        return (
<div>
  <aside className="main-sidebar sidebar-dark-primary elevation-4">
    {/* Brand Logo */}
    <a href="index3.html" className="brand-link">
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
                {/* <a href="./Add.html" className="nav-link">
                  <i className="far fa-circle nav-icon" />
                  <p>Add Reimbursement</p> */}
                  <Anchor>
                    <Link href="#add" className="nav-link" title="Add Reimbursement"/>
                  </Anchor>
                {/* </a> */}
              </li>
              <li className="nav-item">
                <a href="./index2.html" className="nav-link">
                  <i className="far fa-circle nav-icon" />
                  <p>View Reimbursement</p>
                </a>
              </li>
              <li className="nav-item">
                <a href="./index3.html" className="nav-link">
                  <i className="far fa-circle nav-icon" />
                  <p>View Attendence</p>
                </a>
              </li>
              <li className="nav-item">
                {/* <a href="./index3.html" className="nav-link">
                  <i className="far fa-circle nav-icon" />
                  <p>Edit Reimbursement</p>
                </a> */}
                 <Anchor>
                    <Link href="#edit" className="nav-link" title="Edit Reimbursement"/>
                </Anchor>
              </li>
            </ul>
          </li>   
        </ul>
      </nav>
    </div>
  </aside>
</div>
        )
    }
}
