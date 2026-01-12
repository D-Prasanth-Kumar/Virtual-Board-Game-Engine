import React, { useState } from 'react';
import axios from 'axios';
import snakeImg from '../assets/snake.png';
import ladderImg from '../assets/ladder.png';

const SPECIAL_CELLS = { 3: 22, 8: 26, 20: 29, 97: 78, 95: 56, 88: 24 };

const SnakeLadder = () => {
  
  const [players, setPlayers] = useState({ p1: "PLAYER_1", p2: "PLAYER_2" });
  const [positions, setPositions] = useState({}); 
  const [status, setStatus] = useState("WAITING_FOR_ENTITIES");
  const [isStarted, setIsStarted] = useState(false);
  const [logs, setLogs] = useState(["> System Standby..."]);
  const [isRolling, setIsRolling] = useState(false);
  const [diceValue, setDiceValue] = useState(1);
  const [gameOver, setGameOver] = useState(false);
  const API_BASE = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";


  const renderBoard = () => {
    let rows = [];
    for (let r = 0; r < 10; r++) {
      let rowCells = [];
      for (let c = 0; c < 10; c++) {
        let cellNum = (9 - r) * 10 + (r % 2 === 0 ? 10 - c : c + 1);
        rowCells.push(cellNum);
      }
      rows.push(rowCells);
    }
    return rows.flat();
  };

  const boardCells = renderBoard();

  const getCoords = (num) => {
    const index = boardCells.indexOf(num);
    if (index === -1) return { x: 0, y: 0 };
    return { x: (index % 10) * 50 + 25, y: Math.floor(index / 10) * 50 + 25 };
  };

  const handleStart = async () => {
    try {
      const res = await axios.post(`${API_BASE}/api/snakes/start`, [players.p1, players.p2]);
      setIsStarted(true);
      setStatus("GAME_ACTIVE");
      setPositions({ [players.p1]: 1, [players.p2]: 1 });
      setLogs(prev => [...prev, `> Session Started: ${players.p1} vs ${players.p2}`]);
    } catch (err) { 
      setStatus("BACKEND_OFFLINE"); 
    }
  };

  const handleRoll = async () => {
    if (isRolling || gameOver) return;
    setIsRolling(true);
    const interval = setInterval(() => setDiceValue(Math.floor(Math.random() * 6) + 1), 80);

    try {
      const res = await axios.post(`${API_BASE}/api/snakes/roll`);
      setTimeout(() => {
        clearInterval(interval);
        setIsRolling(false);
        if (res.data.positions) {
          setDiceValue(res.data.rollValue);
          setPositions(res.data.positions); 
          setLogs(prev => [...prev, `> ${res.data.currentPlayer} rolled ${res.data.rollValue}`]);
        }
        if (res.data.winner) {
          setGameOver(true);
          setStatus(`${res.data.winner.toUpperCase()} WINS!`);
        }
      }, 700);
    } catch (err) { clearInterval(interval); setIsRolling(false); }
  };

  return (
    <div className="flex flex-row gap-12 p-8 animate-in fade-in slide-in-from-bottom-4 duration-700">
      {/* WINNER MODAL */}
      {gameOver && (
        <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/80 backdrop-blur-sm">
          <div className="p-12 border-8 border-yellow-400 bg-gray-900 text-center pixel-border">
            <h2 className="text-4xl font-pixel text-yellow-400 mb-6 uppercase">Mission_Complete</h2>
            <p className="font-mono text-retro-green mb-8 uppercase tracking-widest">{status}</p>
            <button onClick={() => window.location.reload()} className="w-full py-4 bg-retro-green text-black font-pixel hover:bg-green-400 active:translate-y-1">REBOOT_GAME</button>
          </div>
        </div>
      )}

      {!isStarted ? (
        /* REGISTRATION SCREEN */
        <div className="bg-gray-800 p-8 pixel-border flex flex-col gap-6 w-96 shadow-2xl">
          <h3 className="font-pixel text-sm text-center text-yellow-400 uppercase tracking-widest">Register_Entities</h3>
          <div className="flex flex-col gap-2">
            <label className="font-mono text-[10px] text-retro-green opacity-50">USER_01_ID</label>
            <input 
              className="bg-black text-retro-green p-3 border-b-2 border-retro-green font-mono outline-none focus:border-white transition-all"
              value={players.p1} 
              onChange={(e) => setPlayers({...players, p1: e.target.value})} 
            />
          </div>
          <div className="flex flex-col gap-2">
            <label className="font-mono text-[10px] text-retro-green opacity-50">USER_02_ID</label>
            <input 
              className="bg-black text-retro-green p-3 border-b-2 border-retro-green font-mono outline-none focus:border-white transition-all"
              value={players.p2} 
              onChange={(e) => setPlayers({...players, p2: e.target.value})} 
            />
          </div>
          <button 
            onClick={handleStart} 
            className="bg-retro-green text-black font-pixel py-5 mt-4 hover:bg-green-400 active:translate-y-1 transition-all"
          >
            INITIALIZE_CORE
          </button>
        </div>
      ) : (
        /* GAME BOARD AND SIDEBAR */
        <>
          <div className="relative w-[500px] h-[500px] bg-gray-900 border-4 border-retro-green pixel-border shadow-[0_0_30px_rgba(74,222,128,0.2)]">
            <svg className="absolute inset-0 w-full h-full pointer-events-none z-10" viewBox="0 0 500 500">
              {Object.entries(SPECIAL_CELLS).map(([start, end]) => {
                const s = getCoords(parseInt(start)), e = getCoords(end);
                const dist = Math.sqrt(Math.pow(e.x - s.x, 2) + Math.pow(e.y - s.y, 2));
                const angle = Math.atan2(e.y - s.y, e.x - s.x) * (180 / Math.PI) + 90;
                return (
                  <image key={start} href={end > start ? ladderImg : snakeImg} x={s.x - 25} y={s.y - dist} width="50" height={dist} transform={`rotate(${angle}, ${s.x}, ${s.y})`} style={{ opacity: 0.8 }} />
                );
              })}
            </svg>

            <div className="grid grid-cols-10 w-full h-full relative z-0">
              {boardCells.map((num) => (
                <div key={num} className="border border-retro-green/10 flex items-center justify-center relative">
                  <span className="opacity-10 absolute top-0.5 left-0.5 text-[8px]">{num}</span>
                  <div className="flex gap-1 absolute inset-0 items-center justify-center pointer-events-none z-20">
                    {Object.entries(positions).map(([name, pos]) => pos === num && (
                      <div key={name} className={`w-5 h-5 pixel-border animate-bounce transition-all duration-500 ${name === players.p1 ? 'bg-yellow-400 shadow-[0_0_10px_#facc15]' : 'bg-blue-400 shadow-[0_0_10px_#60a5fa]'}`} />
                    ))}
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="flex flex-col gap-6 w-80">
            <div className="bg-gray-800 p-4 border-2 border-retro-green/50 text-center">
              <h2 className={`font-pixel text-sm ${gameOver ? 'text-red-500' : 'text-yellow-400'}`}>{status}</h2>
            </div>
            
            <div className="flex items-center justify-center gap-6 bg-black p-6 border-2 border-retro-green">
              <div className="w-16 h-16 bg-white flex items-center justify-center pixel-border text-black font-pixel text-2xl shadow-inner">
                {diceValue}
              </div>
              <button 
                onClick={handleRoll} 
                disabled={isRolling} 
                className={`flex-1 py-4 font-pixel transition-all ${isRolling ? 'bg-gray-700 text-gray-500' : 'bg-yellow-400 text-black hover:bg-yellow-300 active:translate-y-1'}`}
              >
                {isRolling ? "BUSY..." : "ROLL"}
              </button>
            </div>
            
            <div className="bg-black p-4 border-2 border-retro-green/30 h-48 overflow-y-auto text-[10px] text-retro-green font-mono custom-scrollbar">
               {logs.slice().reverse().map((log, i) => <div key={i} className="mb-1 border-b border-white/5 pb-1 opacity-80">{log}</div>)}
            </div>
          </div>
        </>
      )}
    </div>
  );
};

export default SnakeLadder;