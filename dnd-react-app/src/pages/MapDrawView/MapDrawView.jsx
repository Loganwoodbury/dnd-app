import BoardComponent from "../../components/BoardComponent/BoardComponent"
import MapMenuComponent from "../../components/MapMenuComponent/MapMenu"
import { useState } from "react";

export default function MapDrawView() {

    const [lineWidth, setLineWidth] = useState(5);
    const [lineColor, setLineColor] = useState("black");


    return (
        <>
            <h1>Battle Map</h1>
            <MapMenuComponent onColorChange={setLineColor} onWidthChange={setLineWidth}/>
            <BoardComponent lineColor={lineColor} lineWidth={lineWidth}/>
        </>
    )
}