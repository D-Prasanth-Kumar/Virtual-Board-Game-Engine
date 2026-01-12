import React from 'react';

const Dice = ({ value, rolling }) => {
  
  const renderDots = (num) => {
    const dotPositions = {
      1: [4],
      2: [0, 8],
      3: [0, 4, 8],
      4: [0, 2, 6, 8],
      5: [0, 2, 4, 6, 8],
      6: [0, 2, 3, 5, 6, 8],
    };

    return Array.from({ length: 9 }).map((_, i) => (
      <div key={i} className="w-2 h-2 flex items-center justify-center">
        {dotPositions[num]?.includes(i) && (
          <div className="w-full h-full bg-black rounded-full shadow-inner" />
        )}
      </div>
    ));
  };

  return (
    <div className={`w-16 h-16 bg-white pixel-border flex items-center justify-center p-2 
      ${rolling ? 'animate-bounce' : ''}`}>
      <div className="grid grid-cols-3 grid-rows-3 gap-1 w-full h-full">
        {renderDots(value || 1)}
      </div>
    </div>
  );
};

export default Dice;