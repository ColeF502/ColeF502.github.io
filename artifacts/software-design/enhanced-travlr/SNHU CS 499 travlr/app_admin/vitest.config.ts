import { defineConfig } from 'vitest/config';

export default defineConfig({
  test: {
    // Runs the test files one at a time to reduce memory usage
    fileParallelism: false
  }
});