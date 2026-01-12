import React, { useState } from 'react';
import axios from 'axios';

const TicTacToe = () => {
  const [gameState, setGameState] = useState({
    board: [["-", "-", "-"], ["-", "-", "-"], ["-", "-", "-"]],
    status: "READY",
    gameOver: false,
    winner: null,
    currentPlayer: "PLAYER_X"
  });
  const [players, setPlayers] = useState({ p1: "PLAYER_X", p2: "PLAYER_O" });
  const [isStarted, setIsStarted] = useState(false);

  const handleStart = async () => {
    try {
      const res = await axios.post('http://localhost:8080/api/tictactoe/start', {
        player1: players.p1,
        player2: players.p2
      });
      setGameState(res.data);
      setIsStarted(true);
    } catch (err) {
      setGameState(prev => ({ ...prev, status: "BACKEND_OFFLINE" }));
    }
  };

  const handleMove = async (row, col) => {
    if (!isStarted || gameState.gameOver) return;
    try {
      const res = await axios.post('http://localhost:8080/api/tictactoe/move', { row, col });
      setGameState(res.data);
    } catch (err) {
      console.error("Invalid Move");
    }
  };

  return (
    <div className="relative flex flex-col items-center">
      {/* WINNER / DRAW MODAL */}
      {gameState.gameOver && (
        <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/80 backdrop-blur-sm animate-in zoom-in duration-300">
          <div className="p-12 border-8 border-double border-yellow-400 bg-gray-900 text-center pixel-border">
            <h2 className="mb-6 text-4xl font-pixel text-yellow-400">
              {gameState.status === "DRAW" ? "STALEMATE!" : "VICTORY!"}
            </h2>
            <p className="mb-8 font-mono text-retro-green uppercase">
              {gameState.status === "DRAW" ? "EQUILIBRIUM REACHED" : `${gameState.winner} dominated the grid.`}
            </p>
            <button onClick={() => window.location.reload()} className="w-full py-4 bg-retro-green text-black font-pixel hover:bg-green-400 active:translate-y-1">
              REINITIALIZE
            </button>
          </div>
        </div>
      )}

      {/* TURN INDICATOR */}
      {isStarted && !gameState.gameOver && (
        <div className={`mb-8 px-8 py-2 border-2 transition-all ${gameState.currentPlayer === players.p1 ? 'border-red-500 text-red-500 shadow-[0_0_10px_red]' : 'border-blue-500 text-blue-500 shadow-[0_0_10px_blue]'}`}>
          <span className="font-pixel text-sm uppercase">ACTIVE_USER: {gameState.currentPlayer}</span>
        </div>
      )}

      {!isStarted ? (
        <div className="bg-gray-800 p-8 pixel-border flex flex-col gap-6 w-80">
          <h3 className="font-pixel text-xs text-center text-yellow-400">REGISTER_ENTITIES</h3>
          <input className="bg-black text-retro-green p-3 border-b-2 border-retro-green font-mono outline-none focus:border-white transition-all"
            value={players.p1} onChange={(e) => setPlayers({...players, p1: e.target.value})} />
          <input className="bg-black text-retro-green p-3 border-b-2 border-retro-green font-mono outline-none focus:border-white transition-all"
            value={players.p2} onChange={(e) => setPlayers({...players, p2: e.target.value})} />
          <button onClick={handleStart} className="bg-retro-green text-black font-pixel py-4 mt-2 hover:bg-green-400 active:translate-y-1 transition-all">START_GAME</button>
        </div>
      ) : (
        <div className="grid grid-cols-3 gap-3 bg-retro-green/20 p-3 pixel-border backdrop-blur-sm">
          {gameState.board.map((row, rIdx) => 
            row.map((cell, cIdx) => (
              <button key={`${rIdx}-${cIdx}`} onClick={() => handleMove(rIdx, cIdx)}
                className="w-24 h-24 bg-gray-900 flex items-center justify-center text-5xl hover:bg-gray-800 transition-all active:scale-95 group">
                <span className={`${cell === 'X' ? 'text-red-500 drop-shadow-[0_0_8px_red]' : 'text-blue-500 drop-shadow-[0_0_8px_blue]'} font-pixel`}>
                  {cell === "-" ? "" : cell}
                </span>
              </button>
            ))
          )}
        </div>
      )}
    </div>
  );
};

export default TicTacToe;