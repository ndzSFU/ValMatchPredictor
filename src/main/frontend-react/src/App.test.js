import { render, screen } from '@testing-library/react';
import Predict from './Predict';

test('renders learn react link', () => {
  render(<Predict />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
