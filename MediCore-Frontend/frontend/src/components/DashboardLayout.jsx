import React, {useState} from 'react'
import AppSidebar from "./Sidebar.jsx";
import {Button, Icon, Sidebar as SUISidebar} from "semantic-ui-react";
import {Outlet} from "react-router-dom";

const DashboardLayout = () => {

    const HEADER_HEIGHT = 60
    const SIDEBAR_WIDTH = 150;
    const [visible, setVisible] = useState(false);

    return (
        <div style={{height: '100vh', overflow: 'hidden'}}>
            <div
                style={{
                    height: HEADER_HEIGHT,
                    backgroundColor: '#2c3757',
                    display: 'flex',
                    alignItems: 'center',
                    padding: '0 1rem',
                    color: 'white',
                    position: 'fixed',
                    width: '100%',
                    zIndex: 1100,
                    top: 0,
                    left: 0,
                }}
            >
                <Button icon onClick={() => setVisible(!visible)} basic inverted>
                    <Icon name="bars"/>
                </Button>
                <h3 style={{marginLeft: '1rem', marginBottom: '14px'}}>MediCore</h3>
            </div>

            <div style={{
                marginTop: HEADER_HEIGHT,
                height: `calc(100vh - ${HEADER_HEIGHT}px)`,
                display: 'flex'
            }}>
                <AppSidebar visible={visible} onHide={() => setVisible(false)}/>

                <div
                    style={{
                        marginLeft: visible ? SIDEBAR_WIDTH : 0,
                        flex: 1,
                        backgroundColor: '#f9f9ff',
                        overflowY: 'auto',
                        transition: 'margin-left 0.3s ease-in-out',
                        height: '100%'
                    }}
                >
                    <div style={{padding: '2rem'}}>
                        <Outlet/>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default DashboardLayout;