import React from "react";
import "../Stylesheets/mystyle.css";

function CalculateScore(props) {

    const average = (props.total / props.goal).toFixed(2);

    return (
        <div className="container">
            <h2>Student Score Calculator</h2>

            <p><strong>Name:</strong> {props.name}</p>

            <p><strong>School:</strong> {props.school}</p>

            <p><strong>Total Score:</strong> {props.total}</p>

            <p><strong>Goal:</strong> {props.goal}</p>

            <p><strong>Average Score:</strong> {average}</p>
        </div>
    );
}

export default CalculateScore;