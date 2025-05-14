import { useEffect, useState, useRef } from 'react';
import styles from './BoardComponent.module.css';
export default function BoardComponent({lineColor, lineWidth}) {

    const canvasRef = useRef(null);
    const ctxRef = useRef(null);
    const [isDrawing, setIsDrawing] = useState(false);
    

    useEffect(() => {
        loadBoard();
        
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
    }

    function stopDrawing(){
        setIsDrawing(false);
        ctxRef.current.closePath();
    }
    function draw(e){
        if (!isDrawing) return;

        ctxRef.current.lineTo(e.nativeEvent.offsetX, e.nativeEvent.offsetY);
        ctxRef.current.stroke();
    }

    function text(e){
        ctxRef.current.fillText(e.target.value, e.nativeEvent.offsetX, e.nativeEvent.offsetY);
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
                    ref={canvasRef}>

                </canvas>
            </div>
        
        </>
    )
}