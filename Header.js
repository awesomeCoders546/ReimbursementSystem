import React, { Component } from 'react'
import { Anchor } from 'antd';

const { Link } = Anchor;
export default class Header extends Component {
    render() {
        return (
            <div id="home">
            <nav className="main-header navbar navbar-expand navbar-white navbar-light">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <a className="nav-link" data-widget="pushmenu" href="#"><i className="fas fa-bars" /></a>
                </li>
                 <li className="nav-item d-none d-sm-inline-block">
                      <Anchor>
                      <Link href="#home" className="nav-link" title="Home" />
                      </Anchor>
                  </li>
                  <li className="nav-item d-none d-sm-inline-block">
                    <Anchor>
                        <Link href="#add"   className="nav-link" title="Add" />
                    </Anchor>
                  </li>
                  <li className="nav-item d-none d-sm-inline-block">
                    <Anchor>
                      <Link href="#edit"  className="nav-link" title="Edit"/>
                    </Anchor>
                   </li>
              </ul>
            </nav>
            <Anchor>
            <Link href="#home" title="Home" />
             <Link href="#add" title="Add" />
            <Link href="#edit" title="Edit"/>
             </Anchor>
          </div>
        )
    }
}
