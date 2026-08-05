import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const mockJwtToken =
    'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwidXNlcm5hbWUiOiJ1c2VyIiwiaWF0IjoxNTE2MjM5MDIyfQ.mock-signature-here';
  const cloned = req.clone({
    setHeaders: {
      Authorization: `Bearer ${mockJwtToken}`,
    },
  });
  return next(cloned);
};
