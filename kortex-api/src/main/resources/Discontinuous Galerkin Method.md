---
sticker: lucide//newspaper
---
The **Discontinuous Galerkin (DG) Method** is a numerical technique for solving differential equations, similar to the Galerkin method but with a key difference: the solution is allowed to be discontinuous across element boundaries. This makes the method particularly well-suited for problems involving complex geometries, shocks, or discontinuities, such as those encountered in **fluid dynamics** and **hyperbolic partial differential equations (PDEs)**.

### 1. **Key Differences from Standard Galerkin Method**

In the standard Galerkin method (used in **finite element analysis**, for example), the solution is typically required to be **continuous** across the entire domain. The **Discontinuous Galerkin Method** allows the solution to be piecewise continuous within each element, but **discontinuous** between elements. This means:
- The domain is divided into subdomains (or elements), and in each subdomain, the solution is approximated using local basis functions.
- There is no requirement that the solution matches exactly at the boundaries between subdomains.

The DG method combines aspects of **finite volume** and **finite element methods**, providing high flexibility while still retaining accuracy.

### 2. **Core Idea**

The main idea of the Discontinuous Galerkin method is to solve the PDE in each element (subdomain) independently and then enforce **weak continuity** across the element boundaries through the use of **numerical fluxes** and **penalty terms**. This weak continuity ensures that even though the solutions may be discontinuous at the boundaries, the physical principles governing the PDE are still satisfied in an average or integral sense.

### 3. **Formulation of the Discontinuous Galerkin Method**

Consider a simple one-dimensional PDE for demonstration:

$$
\frac{du}{dx} = f(x)
$$

In the DG method, we:
- **Divide the domain** into elements $[x_i, x_{i+1}]$ for $i = 1, \dots, N$.
- **Approximate the solution** $u(x)$ in each element using local basis functions $\phi_i(x)$, but without requiring continuity between the elements.

#### Steps:
1. **Local Approximation**: In each element, the solution is represented as a linear combination of local basis functions, for example:
   $$
   u_N(x) = \sum_{i=1}^{N} c_i \phi_i(x)
   $$
   where the coefficients  $c_i$ are different for each element.

2. **Weak Formulation**: The PDE is integrated against test functions (which could be the same as the basis functions) over each element. For a differential operator $\mathcal{L}$, the weak form in an element $K$ would be:
   $$
   \int_K \mathcal{L}(u_N) \phi_j \, dx = \int_K f(x) \phi_j \, dx
   $$
   where $\phi_j$ are the basis functions in the element $K$.

3. **Numerical Fluxes**: To deal with the discontinuity between elements, **numerical fluxes** are introduced at the boundaries of the elements. These fluxes approximate the interaction between adjacent elements and ensure stability and accuracy. The numerical flux $\hat{f}(u^+, u^-)$ depends on the values of the solution from both sides of the boundary (i.e., $u^+$ from the current element and $u^-$ from the neighboring element).

4. **Penalty Terms**: To enforce stability, penalty terms may be added to penalize large jumps between elements at the boundaries.

---

### 4. **Advantages of the Discontinuous Galerkin Method**

- **Flexibility**: The DG method can handle complex geometries and irregular meshes more easily than continuous methods, due to the flexibility of allowing discontinuities.
- **Locality**: Since the solution is discontinuous, each element can be solved somewhat independently, making the method highly parallelizable and well-suited for modern computing architectures.
- **Handling Discontinuities**: It is particularly effective for problems with sharp gradients, shocks, or discontinuities, such as in fluid dynamics (e.g., solving Euler equations for compressible flow).
- **High-Order Accuracy**: DG methods can achieve high-order accuracy by increasing the number of local basis functions in each element.

---

### 5. **Applications of Discontinuous Galerkin Method**

- **Computational Fluid Dynamics (CFD)**: DG methods are widely used in CFD to model compressible flows and other problems involving shock waves or discontinuities.
- **Electromagnetics**: In electromagnetics, DG methods can handle wave propagation problems in complex domains.
- **Hyperbolic PDEs**: DG is particularly effective for hyperbolic PDEs, which often model wave propagation, fluid dynamics, and other time-dependent phenomena where discontinuities or shocks are present.

---

### 6. **Mathematica Example: Simple DG Method**

Let’s use **Mathematica** to demonstrate a basic setup of the DG method for a 1D problem.

Consider the same differential equation:

$$
\frac{du}{dx} = -1, \quad u(0) = 0, \quad u(1) = 0
$$

1. **Define the Domain and Basis Functions**:

We divide the domain into two elements: $[0, 0.5]$ and $[0.5, 1]$. In each element, we will use a local linear basis function.

```mathematica
(* Define the two subdomains *)
subdomain1 = {x, 0, 0.5};
subdomain2 = {x, 0.5, 1};

(* Define the local basis functions for each element *)
phi1[x_] := x (1 - x)
phi2[x_] := (x - 0.5) (1 - x)

(* Local approximations in each element *)
u1[x_] := c1 phi1[x];
u2[x_] := c2 phi2[x];
```

2. **Define the Residuals and Weak Form**:

For each element, we calculate the residual and enforce the weak formulation.

```mathematica
(* Residuals in each element *)
residual1 = D[u1[x], x] + 1;
residual2 = D[u2[x], x] + 1;

(* Weak formulation: Integrate residuals over the elements *)
eqn1 = Integrate[residual1 * phi1[x], {x, 0, 0.5}] == 0;
eqn2 = Integrate[residual2 * phi2[x], {x, 0.5, 1}] == 0;
```

3. **Numerical Fluxes**:

We now compute the numerical flux at the interface $x = 0.5$. In the DG method, this is where we enforce the conditions to deal with the discontinuity between the two elements.

```mathematica
(* Define the numerical flux at the interface (x = 0.5) *)
fluxInterface = (u1[0.5] + u2[0.5])/2; (* Average flux *)

(* Add penalty terms to ensure stability at the interface *)
penalty = (u2[0.5] - u1[0.5]);
```

4. **Solve for Coefficients**:

We solve for the coefficients $c_1$ and  $c_2$ by solving the weak form equations and flux continuity.

```mathematica
(* Solve the system of equations for c1 and c2 *)
sol = Solve[{eqn1, eqn2, fluxInterface == 0, penalty == 0}, {c1, c2}]
```

5. **Plot the Solution**:

Finally, we plot the solution in each subdomain.

```mathematica
(* Substitute solution and plot *)
uSol1[x_] = u1[x] /. sol;
uSol2[x_] = u2[x] /. sol;

Plot[{uSol1[x], uSol2[x]}, {x, 0, 1}, PlotRange -> All, 
 PlotLabel -> "Discontinuous Galerkin Approximation"]
```

---

### 7. **Summary**

The **Discontinuous Galerkin Method** combines the flexibility of discontinuous solutions with the accuracy of Galerkin methods. It is especially useful for problems involving complex geometries or discontinuities (such as shock waves) and is highly parallelizable.

In this example, we:
- **Divided the domain** into elements.
- **Approximated the solution** within each element using local basis functions.
- Introduced **numerical fluxes** and **penalty terms** to handle discontinuities at the interfaces.
- Solved the system of equations and plotted the **discontinuous solution**.

