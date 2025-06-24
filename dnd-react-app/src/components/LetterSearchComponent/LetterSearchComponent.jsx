import styles from './LetterSearchComponent.module.css';

export default function LetterSearchComponent({ onLetterClick}) {




    return (

        <div className = {styles.letterSearch}>
            <span id="filter_A" onClick={() => onLetterClick("A")}>A</span> | 
            <span id="filter_B" onClick={() => onLetterClick("B")}> B</span> | 
            <span id="filter_C" onClick={() => onLetterClick("C")}> C</span> | 
            <span id="filter_D" onClick={() => onLetterClick("D")}> D</span> | 
            <span id="filter_E" onClick={() => onLetterClick("E")}> E</span> | 
            <span id="filter_F" onClick={() => onLetterClick("F")}> F</span> | 
            <span id="filter_G" onClick={() => onLetterClick("G")}> G</span> | 
            <span id="filter_H" onClick={() => onLetterClick("H")}> H</span> | 
            <span id="filter_I" onClick={() => onLetterClick("I")}> I</span> | 
            <span id="filter_J" onClick={() => onLetterClick("J")}> J</span> | 
            <span id="filter_K" onClick={() => onLetterClick("K")}> K</span> | 
            <span id="filter_L" onClick={() => onLetterClick("L")}> L</span> | 
            <span id="filter_M" onClick={() => onLetterClick("M")}> M</span> | 
            <span id="filter_N" onClick={() => onLetterClick("N")}> N</span> | 
            <span id="filter_O" onClick={() => onLetterClick("O")}> O</span> | 
            <span id="filter_P" onClick={() => onLetterClick("P")}> P</span> | 
            <span id="filter_Q" onClick={() => onLetterClick("Q")}> Q</span> | 
            <span id="filter_R" onClick={() => onLetterClick("R")}> R</span> | 
            <span id="filter_S" onClick={() => onLetterClick("S")}> S</span> | 
            <span id="filter_T" onClick={() => onLetterClick("T")}> T</span> | 
            <span id="filter_U" onClick={() => onLetterClick("U")}> U</span> | 
            <span id="filter_V" onClick={() => onLetterClick("V")}> V</span> | 
            <span id="filter_W" onClick={() => onLetterClick("W")}> W</span> | 
            <span id="filter_X" onClick={() => onLetterClick("X")}> X</span> | 
            <span id="filter_Y" onClick={() => onLetterClick("Y")}> Y</span> | 
            <span id="filter_Z" onClick={() => onLetterClick("Z")}> Z</span>
        </div>
    )

}