import { ReactElement } from 'react';
import { useNavigate } from 'react-router-dom';

const SidebarLogo = (): ReactElement => {
  const navigate = useNavigate();

  const handleLogoClick = (): void => {
    navigate('/');
  };

  return (
    <div
      className="flex cursor-pointer items-center justify-center gap-2"
      onClick={handleLogoClick}
    >
      <img
        src="/zhimeng-ai-icon.svg"
        className="h-10 w-10 flex-shrink-0"
        alt="zhimeng-ai"
      />
      <span
        className="text-[30px] font-semibold leading-none"
        style={{ color: '#334155' }}
      >
        zhimeng-ai
      </span>
    </div>
  );
};

export default SidebarLogo;
