import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Predict from './Predict';
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider, Link} from "react-router-dom";
import TeamProfiles from "./TeamProfiles";
import HomePage from "./HomePage";


function ErrorPage(){
    return(
        <div className="flex flex-col gap-2">
            Sorry Couldn't find the URL you requested.
            <Link to="/">Go To Home Page</Link>
        </div>

    );
}

const router = createBrowserRouter([
    {
      path: '/',
      element: <HomePage/>,
      errorElement: <ErrorPage/>
    },
    {
        path: '/Predict',
        element: <Predict/>
    },
    {
        path: '/Team',
        element: <TeamProfiles/>
    }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
