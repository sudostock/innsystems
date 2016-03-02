Particle Swarm Optimization notes:

adjust position, velocity:
if presentx[.md](.md) > pbestx[gbest](gbest.md) then vx[.md](.md) = vx[.md](.md) - rand()**g\_increment
if presentx[.md](.md) < pbestx[gbest](gbest.md) then vx[.md](.md) = vx[.md](.md) + rand()**g\_increment
if presenty[.md](.md) > pbesty[gbest](gbest.md) then vy[.md](.md) = vy[.md](.md) - rand()**g\_increment
if presenty[.md](.md) < pbesty[gbest](gbest.md) then vy[.md](.md) = vy[.md](.md) + rand()**g\_increment

+Each Particle has:
-memory of their own best position and knowledge of the swarm's best

++Pseudo
g = global best position;
xxi = current best position for each particle;

+initialize positions and velocities of all particles:

+adjust positions of particles:
> new xi= previous xi + vi;

+adjust velocities of particles:
> new vi= (w)vi + c1r1 o (g-xi) + c2r2 o (g-xi)
> -where w is an inertial constant. Good values are usually slightly less than 1,
> -c1 and c2 are constants that say how much the particle is directed towards
> > good positions. They represent a "cognitive" and a "social" component,
> > respectively, in that they affect how much the particle's personal best
> > and the global best (respectively) influence its movement.

> -[r1](https://code.google.com/p/innsystems/source/detail?r=1) and [r2](https://code.google.com/p/innsystems/source/detail?r=2) are two random vectors with each compenent generally a uniform
> > random number between 0 and 1

> -and the o operator indicates element-by-element multiplication
> -MAximum velocity
+Pseudo for adjusting velocity and position:

> -for I = 1 to number of particles n do
> for J=1 to number of dimensions m do
> > [R1](https://code.google.com/p/innsystems/source/detail?r=1)=uniform random number
> > [R2](https://code.google.com/p/innsystems/source/detail?r=2)=uniform random number
> > V[I](I.md)[J](J.md)=w\*V[I](I.md)[J](J.md)
> > > +C1\*[R1](https://code.google.com/p/innsystems/source/detail?r=1)**(P[I](I.md)[J](J.md)-X[I](I.md)[J](J.md))
> > > +C2\*[R2](https://code.google.com/p/innsystems/source/detail?r=2)**(G[I](I.md)[J](J.md)-X[I](I.md)[J](J.md))

> enddo
> X[I](I.md)[J](J.md) = X[I](I.md)[J](J.md)+V[I](I.md)[J](J.md)
> enddo









