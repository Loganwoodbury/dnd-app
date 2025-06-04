import { useEffect, useState, useRef } from 'react';
import styles from './BoardComponent.module.css';
export default function BoardComponent({lineColor, lineWidth}) {

    const canvasRef = useRef(null);
    const ctxRef = useRef(null);
    const [isDrawing, setIsDrawing] = useState(false);

     // Define default grid properties
     const gridColor = '#e0e0e0'; // A light gray for the grid
     const gridLineWidth = 1;     // Thin lines for the grid
    

    useEffect(() => {
        loadBoard();
        drawGrid(gridLineWidth, 20, 20, gridColor);
    }, []);
    
    
    useEffect(() => {
    
    }, [lineColor, lineWidth]);

    function loadBoard(){
        const canvas = canvasRef.current;
        if (canvas) {
            const ctx = canvas.getContext('2d');
            ctx.lineCap = 'round';
            ctx.lineJoin = 'round';
            ctx.strokeStyle = lineColor;
            ctx.lineWidth = lineWidth;
            ctxRef.current = ctx;
        }
    }

    function startDrawing(e){
        setIsDrawing(true);
        ctxRef.current.beginPath();
        ctxRef.current.moveTo(e.nativeEvent.offsetX, e.nativeEvent.offsetY);
        ctxRef.current.strokeStyle = lineColor;
        ctxRef.current.lineWidth = lineWidth;
    }

    function stopDrawing(){
        setIsDrawing(false);
        ctxRef.current.closePath();
        ctxRef.current.save();
    }
    function draw(e){
        if (!isDrawing) return;

        ctxRef.current.lineTo(e.nativeEvent.offsetX, e.nativeEvent.offsetY);
        ctxRef.current.stroke();
    }

    function text(e){
        ctxRef.current.fillText(e.target.value, e.nativeEvent.offsetX, e.nativeEvent.offsetY);
    }

    function drawGrid(lineWidth, cellWidth, cellHeight, color){
        const canvas = canvasRef.current;
        const ctx = canvas.getContext('2d');
        ctx.strokeStyle = color;
        ctx.lineWidth = lineWidth;
        console.log("drawing grid");

        //get size
        let width = canvas.width;
        let height = canvas.height;

        //draw vertical lines
        for(let x = 0; x <=width; x += cellWidth){
            ctx.beginPath();
            ctx.moveTo(x,0);
            ctx.lineTo(x, height);
            ctx.stroke();
        }

        for(let y = 0; y <= height; y += cellHeight){
            ctx.beginPath();
            ctx.moveTo(0,y);
            ctx.lineTo(width, y);
            ctx.stroke();
        }

        ctx.save();
    } 
    
    function handleClear(){
        const canvas = canvasRef.current;
        ctxRef.current.clearRect(0,0, canvas.width, canvas.height);
        drawGrid(gridLineWidth, 20, 20, gridColor);
    }

    function handleBackgroundChange() {
    }


    return (
        <>
            <div className={styles.gameBoardContainer}>
                <canvas id="gameBoard" className={styles.gameBoard}
                    onMouseDown={startDrawing}
                    onMouseUp={stopDrawing}
                    onMouseMove={draw}
                    onKeyDown= {text}
                    width="800"
                    height="800"
                    ref={canvasRef}
                    style={{backgroundImage: 'url("/vite.svg")', backgroundSize: 'cover'}}>
                    

                </canvas>
            </div>

            <button onClick={handleClear}>Clear Board</button>
            <button onClick={handleBackgroundChange}>Change Map Background</button>
            
        
        </>
    )
}