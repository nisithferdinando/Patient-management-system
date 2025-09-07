import React from 'react'
import {Link} from "react-router-dom";
import {Sidebar, Menu, Icon} from "semantic-ui-react";

const AppSidebar = ({visible, onHide}) => {
    return (
    <div>
        <Sidebar
        as={Menu}
        animation="push"
        icon="labeled"
        inverted
        vertical
        visible={visible}
        width="thin"
        style={{
            backgroundColor: "#232c45",
           top:60, left:0,
            height: 'calc(100vh - 60px)',
            position: 'fixed',
         zIndex: 999,
            overflowY: 'auto'
        }}
        >
        <Menu.Item as={Link} to="/home" onClick={onHide} >
            <Icon name="home"/>
           Home
        </Menu.Item>
        <Menu.Item as={Link} to="/doctors" onClick={onHide}>
            <Icon name="user md"/>
            Doctors
        </Menu.Item>
        <Menu.Item as={Link} to="/patients" onClick={onHide}>
            <Icon name="users"/>
            Patients
        </Menu.Item>
            <Menu.Item as={Link} to="/addmission" onClick={onHide}>
                <Icon name="user"/>
                Admissions
            </Menu.Item>
            <Menu.Item as={Link} to="/patients-rooms" onClick={onHide}>
                       <Icon name="warehouse"/>
                Patients Rooms
                </Menu.Item>
            <Menu.Item as={Link} to="/food" onClick={onHide}>
                <Icon name="food"/>
                Patients Food
            </Menu.Item>
        </Sidebar>

        </div>
    )
}
export default AppSidebar
