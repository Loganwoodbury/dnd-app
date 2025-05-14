
export default function MapMenuComponent({onColorChange, onWidthChange}){


    return (
        <>
        
            <div className="map-menu">
                <label>Line Color</label>
                <input type="color" onChange={(e) => onColorChange(e.target.value)} />
                <label>Line Width</label>
                <input type="number" min="1" max="20" onChange={(e) => onWidthChange(e.target.value)} />
            </div>
        
        </>
    )

}