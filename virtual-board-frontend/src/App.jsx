import React, { useState } from 'react';
import TicTacToe from './components/TicTacToe';
import SnakeLadder from './components/SnakeLadder';

function App() {
  const [activeGame, setActiveGame] = useState('menu');

  const MainMenu = () => (
    <div className="flex flex-col items-center gap-8 animate-in fade-in zoom-in duration-500">
      <h1 className="text-5xl font-pixel text-retro-green animate-pulse mb-10 tracking-widest">
        SELECT_MODULE
      </h1>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-10">
        <button 
          onClick={() => setActiveGame('tictactoe')}
          className="p-8 bg-gray-800 pixel-border hover:bg-retro-green hover:text-black transition-all group cursor-pointer active:translate-y-1"
        >
          <h2 className="text-2xl font-pixel mb-4">TIC-TAC-TOE</h2>
          <p className="font-mono text-lg opacity-70 group-hover:opacity-100 uppercase">3x3_Logic_Gate</p>
        </button>

        <button 
          onClick={() => setActiveGame('snakes')}
          className="p-8 bg-gray-800 pixel-border hover:bg-yellow-400 hover:text-black transition-all group cursor-pointer active:translate-y-1"
        >
          <h2 className="text-2xl font-pixel mb-4">SNAKES_LADDERS</h2>
          <p className="font-mono text-lg opacity-70 group-hover:opacity-100 uppercase">Linear_Probability</p>
        </button>
      </div>
    </div>
  );

  return (
    <div className="min-h-screen bg-gray-950 text-retro-green flex flex-col items-center justify-center p-4">
      {/* Top Navigation */}
      {activeGame !== 'menu' && (
        <nav className="fixed top-0 left-0 right-0 p-6 flex justify-between items-center bg-gray-900/50 backdrop-blur-md z-50 border-b border-retro-green/20">
          <div className="font-pixel text-xs tracking-widest">SYS_ACTIVE // {activeGame.toUpperCase()}</div>
          <button 
            onClick={() => setActiveGame('menu')}
            className="font-pixel text-xs text-red-400 hover:text-white transition-colors cursor-pointer"
          >
            [ ESC::TERMINATE_PROCESS ]
          </button>
        </nav>
      )}

      {activeGame === 'menu' && <MainMenu />}
      
      <div className="mt-20">
        {activeGame === 'tictactoe' && <TicTacToe />}
        {activeGame === 'snakes' && <SnakeLadder />}
      </div>

      <footer className="fixed bottom-4 font-mono text-[10px] opacity-30 tracking-[0.2em]">
        VIRTUAL_ENGINE_V4.0 // STABLE_BUILD_2026
      </footer>
    </div>
  );
}

export default App;